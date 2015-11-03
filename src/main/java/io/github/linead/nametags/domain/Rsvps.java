package io.github.linead.nametags.domain;

public class Rsvps {

    private Rsvp[] results;

    private Meta meta;

    public Rsvp[] getResults() {
        return results;
    }

    public void setResults(Rsvp[] results) {
        this.results = results;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
