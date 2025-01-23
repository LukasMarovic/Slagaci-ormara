package com.progi.progi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LocatedatKey implements Serializable {
    private Integer articleid;
    private Integer locationid;
    private Integer closetid;

    public LocatedatKey() {
    }

    public LocatedatKey(Integer articleid, Integer locationid, Integer closetid) {
        this.articleid = articleid;
        this.locationid = locationid;
        this.closetid = closetid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getClosetid() {
        return closetid;
    }

    public void setClosetid(Integer closetid) {
        this.closetid = closetid;
    }
}
