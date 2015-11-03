package io.github.linead.nametags;

import io.github.linead.nametags.domain.Event;
import io.github.linead.nametags.domain.Members;
import io.github.linead.nametags.domain.Rsvps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MeetupData {

    @Autowired
    Environment env;

    private static final String EVENT_URL = "https://api.meetup.com/self/events?scroll=next_upcoming&photo-host=public&page=20&sig_id=29034422&sig=c9d17fea93c5788258d2703a5a5d92f651f65374";

    private static final String RSVP_URL = "https://api.meetup.com/2/rsvps?&sign=true&key={key}&event_id={event_id}&photo-host=public&page=120";

    private static final String MEMBERS_URL = "https://api.meetup.com/2/members?&key={key}&sign=true&photo-host=public&group_id={group_id}&page=100&only=id,joined,photo&offset={page}";

    
    public String getKey() { return env.getProperty("meetup_api.key"); }

    public Event[] getNextMeetups() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Event[]> responseEntity = restTemplate.getForEntity(EVENT_URL, Event[].class);
        Event[] events =responseEntity.getBody();

        return events;

    }

    public Rsvps getRsvps(String eventId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("event_id", eventId);
        params.put("key", getKey());

        ResponseEntity<Rsvps> responseEntity = restTemplate.getForEntity(RSVP_URL, Rsvps.class, params);
        Rsvps rsvps = responseEntity.getBody();
        return rsvps;
    }

//    @Cacheable("members")
    public Map<String, Members.Member> getMembers(String groupId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<String, Object>();
        int page = 0;
        params.put("group_id", groupId);
        params.put("key", getKey());
        params.put("page", page);

        ResponseEntity<Members> responseEntity = restTemplate.getForEntity(MEMBERS_URL, Members.class, params);
        Members members = responseEntity.getBody();

        HashMap<String, Members.Member> memberMap = new HashMap<String, Members.Member>();

        memberMap.putAll(Arrays.asList(members.getResults())
                .stream().filter(s -> s.getId() != null)
                .collect(
                        Collectors.toMap(Members.Member::getId, Function.identity()
                        )));

        //read all pages
        while(!StringUtils.isEmpty(members.getMeta().getNext())) {
            String nextUrl = members.getMeta().getNext();
            params.put("page", ++page);
            responseEntity = restTemplate.getForEntity(MEMBERS_URL, Members.class, params);
            members = responseEntity.getBody();

            memberMap.putAll(Arrays.asList(members.getResults())
                    .stream().filter(s -> s.getId() != null)
                    .collect(
                            Collectors.toMap(Members.Member::getId, Function.identity()
                            )));
        }



        return memberMap;
    }


}
