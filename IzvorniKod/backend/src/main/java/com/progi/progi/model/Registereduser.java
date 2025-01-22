package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "REGISTEREDUSER")
public class Registereduser {
    @Id
    @Column(name = "USERID", nullable = false)
    private Integer id;

    @Column(name = "GEOLOCATION", length = 50)
    private String geolocation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

}