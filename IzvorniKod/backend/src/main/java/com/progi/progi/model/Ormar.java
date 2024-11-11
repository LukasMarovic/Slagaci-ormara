package com.progi.progi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ORMAR")
public class Ormar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sifOrmara;
    private String nazivOrmara;
    private Integer sifKorisnika;

    public Ormar() {}
    public Ormar(Integer sifOrmara, String nazivOrmara, Integer sifKorisnika) {
        this.sifOrmara = sifOrmara;
        this.nazivOrmara = nazivOrmara;
        this.sifKorisnika = sifKorisnika;
    }

    public Integer getSifOrmara() {
        return sifOrmara;
    }

    public void setSifOrmara(Integer sifOrmara) {
        this.sifOrmara = sifOrmara;
    }

    public String getNazivOrmara() {
        return nazivOrmara;
    }

    public void setNazivOrmara(String nazivOrmara) {
        this.nazivOrmara = nazivOrmara;
    }

    public Integer getSifKorisnika() {
        return sifKorisnika;
    }

    public void setSifKorisnika(Integer sifKorisnika) {
        this.sifKorisnika = sifKorisnika;
    }
}
