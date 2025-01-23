package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATION")
public class Location {
    @EmbeddedId
    private LocationKey id;
    private String vrstaLokacije;

    public Location() {
    }

    public Location(LocationKey id, String vrstaLokacije) {
        this.id = id;
        this.vrstaLokacije = vrstaLokacije;
    }

    public LocationKey getId() {
        return id;
    }

    public void setId(LocationKey id) {
        this.id = id;
    }

    public String getVrstaLokacije() {
        return vrstaLokacije;
    }

    public void setVrstaLokacije(String vrstaLokacije) {
        this.vrstaLokacije = vrstaLokacije;
    }
}