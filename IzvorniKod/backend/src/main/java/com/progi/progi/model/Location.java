package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATIONID", nullable = false)
    private Integer id;

    @Column(name = "CLOSETID")
    private Integer closetid;

    @Column(name = "LOCATIONTYPE", length = 20)
    private String locationtype;

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

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

}