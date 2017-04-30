package io.github.linead.nametags.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Attendee {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String id;
    private String name;
    private Date joined;
    private String pictureUrl;
    private String pictureData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; }

    public String getPictureData() {
        return pictureData;
    }

    public void setPictureData(String pictureData) { this.pictureData = pictureData; }

    public String getPhoto() {
        if(this.pictureUrl != null) {
            return String.format("[imageUrl:%s]", pictureUrl);
        } else if (this.pictureData != null) {
            return String.format("image:base64:%s", pictureData);
        } else {
            return null;
        }
    }
}
