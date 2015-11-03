package io.github.linead.nametags.domain;

import java.util.Map;

public class DocmosisRequest {

    private String accessKey;

    private String template;

    private Map<String,Attendee> attendeeMap;


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Attendee> getAttendeeMap() {
        return attendeeMap;
    }

    public void setAttendeeMap(Map<String, Attendee> attendeeMap) {
        this.attendeeMap = attendeeMap;
    }
}
