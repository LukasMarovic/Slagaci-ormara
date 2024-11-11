package com.progi.progi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "KORISNIK")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sifKorisnika;
    private String imeKorisnika;
    private String email;
    private String lozinka;

    public User() {
    }

    public User(Integer sifKorisnika, String imeKorisnika, String email, String lozinka) {
        this.sifKorisnika = sifKorisnika;
        this.imeKorisnika = imeKorisnika;
        this.email = email;
        this.lozinka = lozinka;
    }

    public Integer getSifKorisnika() {
        return sifKorisnika;
    }

    public void setSifKorisnika(Integer sifKorisnika) {
        this.sifKorisnika = sifKorisnika;
    }

    public String getImeKorisnika() {
        return imeKorisnika;
    }

    public void setImeKorisnika(String imeKorisnika) {
        this.imeKorisnika = imeKorisnika;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
