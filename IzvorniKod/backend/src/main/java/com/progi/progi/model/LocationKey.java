package com.progi.progi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LocationKey implements Serializable {
    private Integer id;
    private Integer closetid;

    public LocationKey() {
    }

    public LocationKey(Integer id, Integer closetid) {
        this.id = id;
        this.closetid = closetid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClosetid() {
        return closetid;
    }

    public void setClosetid(Integer closetid) {
        this.closetid = closetid;
    }
}
