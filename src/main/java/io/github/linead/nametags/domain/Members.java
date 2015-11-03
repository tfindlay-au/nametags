package io.github.linead.nametags.domain;

import java.util.Date;

public class Members {

    private Member[] results;
    private Meta meta;

    public Member[] getResults() {
        return results;
    }

    public void setResults(Member[] results) {
        this.results = results;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Member {


        private String id;
        private Date joined;
        private Photo photo;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getJoined() {
            return joined;
        }

        public void setJoined(Date joined) {
            this.joined = joined;
        }

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }
    }

    public static class Photo {

        private String thumb_link;

        public String getThumb_link() {
            return thumb_link;
        }

        public void setThumb_link(String thumb_link) {
            this.thumb_link = thumb_link;
        }
    }
}
