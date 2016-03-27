package io.github.linead.nametags;

import io.github.linead.nametags.domain.Attendee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@EnableCaching
@RestController
public class RaffleController {

    @Autowired
    Nametags nametags;

    Map<String, Set<String>> ineligible = new HashMap<>();

    @RequestMapping("/raffle")
    public Map<String, List<Attendee>> getAttendeeList(@RequestParam(value = "eventId") String eventId) {

        Set<String> ineligibleMembers = ineligible.computeIfAbsent(eventId, s -> new HashSet<String>());
        List<Attendee> attendees = nametags.getAttendeeList(eventId).get("attendees").stream()
                .filter(s -> !ineligibleMembers.contains(s.getId()))
                .collect(Collectors.toList());

        Map<String, List<Attendee>> resultMap = new HashMap<>();
        resultMap.put("attendees", attendees);
        return resultMap;
    }

    @RequestMapping("/raffle/remove")
    public void remove(@RequestParam(value = "eventId") String eventId,
                       @RequestParam(value = "memberId") String memberId) {
        ineligible.computeIfAbsent(eventId, s -> new HashSet<String>()).add(memberId);
    }

    @RequestMapping("/raffle/reset")
    public void reset() {
        ineligible.clear();
    }

}
