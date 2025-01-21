package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @Column(name = "LOCATIONID", nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CLOSETID")
    private Closet closetid;

    @Column(name = "LOCATIONTYPE", length = 20)
    private String locationtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Closet getClosetid() {
        return closetid;
    }

    public void setClosetid(Closet closetid) {
        this.closetid = closetid;
    }

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

}