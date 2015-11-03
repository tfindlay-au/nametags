package io.github.linead.nametags.domain;

import java.util.Date;

public class Attendee {

    private String id;
    private String name;
    private Date joined;
    private String pictureUrl;

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

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPhoto() {
        return pictureUrl == null ? "" : String.format("[imageUrl:%s]", pictureUrl);
    }
}
