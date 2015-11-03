package io.github.linead.nametags.domain;

import java.util.List;
import java.util.Map;

public class DocmosisRequest {

    private String accessKey;

    private String templateName;

    private String outputName;

    private Map<String, List<Attendee>> data;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, List<Attendee>> getData() {
        return data;
    }

    public void setData(Map<String, List<Attendee>> attendeeMap) {
        this.data = attendeeMap;
    }

    public String getOutputName() {
        return outputName;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }
}
