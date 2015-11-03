package io.github.linead.nametags;

import io.github.linead.nametags.domain.Attendee;
import io.github.linead.nametags.domain.Event;
import io.github.linead.nametags.domain.Members;
import io.github.linead.nametags.domain.Rsvp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@EnableCaching
@RestController
public class Nametags implements CommandLineRunner {

    @Autowired
    MeetupData meetup;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Nametags.class, args);
    }

    @RequestMapping("/events")
    public List<Event> getEvents() {
        return Arrays.asList(meetup.getNextMeetups());
    }

    @RequestMapping("/attendees")
    public List<Attendee> getAttendeeList(@RequestParam(value="eventId") String eventId) {

        List<Attendee> attendees = new ArrayList<>();

        Event event = Arrays.asList(meetup.getNextMeetups())
                .stream().filter(e -> eventId.equals(e.getId())).findFirst().get();

        //fetch member info
        Map<String, Members.Member> members = meetup.getMembers(event.getGroup().getId());

        //fetch rsvps
        for(Rsvp rsvp : meetup.getRsvps(eventId).getResults()) {
            if(rsvp.getMember().getMember_id() == null) {
                continue;
            }

            //new attendee
            Attendee att = new Attendee();
            att.setId(rsvp.getMember().getMember_id());
            att.setName(rsvp.getMember().getName());
            //get member
            Members.Member member = members.get(rsvp.getMember().getMember_id());
            if(member != null) {
                att.setJoined(member.getJoined());
                if(member.getPhoto() != null) {
                    att.setPictureUrl(member.getPhoto().getThumb_link());
                }
            }

            attendees.add(att);
        }

        return attendees;

    }

    @Override
    public void run(String... strings) throws Exception {
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("members");
    }
}