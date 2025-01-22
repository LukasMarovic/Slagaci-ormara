package com.progi.progi.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LocationKey implements Serializable {
    private Integer brojLokacije;
    private Integer sifOrmara;

    public LocationKey() {}
    public LocationKey(Integer brojLokacije, Integer sifOrmara) {
        this.brojLokacije = brojLokacije;
        this.sifOrmara = sifOrmara;
    }

    public Integer getBrojLokacije() {
        return brojLokacije;
    }

    public void setBrojLokacije(Integer brojLokacije) {
        this.brojLokacije = brojLokacije;
    }

    public Integer getSifOrmara() {
        return sifOrmara;
    }

    public void setSifOrmara(Integer sifOrmara) {
        this.sifOrmara = sifOrmara;
    }
}
