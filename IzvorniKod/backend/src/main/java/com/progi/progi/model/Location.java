package com.progi.progi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOKACIJA")
public class Location {

    @EmbeddedId
    private LocationKey id;
    private String vrstaLokacije;

    public Location() {}

    public Location(LocationKey id, String vrstaLokacije) {
        this.id = id;
        this.vrstaLokacije = vrstaLokacije;
    }

    public Location(Integer brojLokacije, Integer sifOrmara, String vrstaLokacije) {
        this.id = new LocationKey(brojLokacije, sifOrmara);
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
