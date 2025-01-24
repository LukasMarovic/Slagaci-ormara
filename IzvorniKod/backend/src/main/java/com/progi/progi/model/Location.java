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

    @Column(name = "LOCATIONNUMBER")
    private Integer locationnumber;

    public Location() {
    }

    public Location(Integer closetid, String locationtype, Integer locationnumber) {
        this.closetid = closetid;
        this.locationtype = locationtype;
        this.locationnumber = locationnumber;
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

    public String getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(String locationtype) {
        this.locationtype = locationtype;
    }

    public Integer getLocationnumber() {
        return locationnumber;
    }

    public void setLocationnumber(Integer locationnumber) {
        this.locationnumber = locationnumber;
    }
}