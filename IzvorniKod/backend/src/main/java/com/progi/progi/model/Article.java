package com.progi.progi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ARTIKL")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArtikla;
    private String nazivArtikla;
    /*private byte[] slikaArtikla;
    private String opisArtikla;
    private String opcaKategorija;
    private String kategorijaLezernosti;
    private String glavnaBoja;
    private String sporednaBoja;
    private String stanjeArtikla;
    private String sifOglasivaca;
    */

    public Article() {}
    public Article (Integer idArtikla, String nazivArtikla/*, byte[] slikaArtikla, String opisArtikla, String opcaKategorija, String kategorijaLezernosti,
                                  String glavnaBoja, String sporednaBoja, String stanjeArtikla, String sifOglasivaca*/) {
        this.idArtikla = idArtikla;
        this.nazivArtikla = nazivArtikla;
      /*  this.slikaArtikla = slikaArtikl
        this.opcaKategorija = opcaKategorija;
        this.kategorijaLezernosti = kategorijaLezernosti;
        this.glavnaBoja = glavnaBoja;
        this.sporednaBoja = sporednaBoja;
        this.stanjeArtikla = stanjeArtikla;
        this.sifOglasivaca = sifOglasivaca;*/
    }

    public Integer getIdArtikla() {
        return idArtikla;
    }
    public void setIdArtikla(Integer idArtikla) {
        this.idArtikla = idArtikla;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }
/*
    public byte[] getSlikaArtikla() {
        return slikaArtikla;
    }

    public void setSlikaArtikla(byte[] slikaArtikla) {
        this.slikaArtikla = slikaArtikla;
    }

    public String getOpisArtikla() {
        return opisArtikla;
    }
    public void setOpisArtikla(String opisArtikla) {
        this.opisArtikla = opisArtikla;
    }

    public String getOpcaKategorija() {
        return opcaKategorija;
    }

    public void setOpcaKategorija(String opcaKategorija) {
        this.opcaKategorija = opcaKategorija;
    }

    public String getKategorijaLezernosti() {
        return kategorijaLezernosti;
    }

    public void setKategorijaLezernosti(String kategorijaLezernosti) {
        this.kategorijaLezernosti = kategorijaLezernosti;
    }

    public String getGlavnaBoja() {
        return glavnaBoja;
    }

    public void setGlavnaBoja(String glavnaBoja) {
        this.glavnaBoja = glavnaBoja;
    }

    public String getSporednaBoja() {
        return sporednaBoja;
    }

    public void setSporednaBoja(String sporednaBoja) {
        this.sporednaBoja = sporednaBoja;
    }

    public String getStanjeArtikla() {
        return stanjeArtikla;
    }

    public void setStanjeArtikla(String stanjeArtikla) {
        this.stanjeArtikla = stanjeArtikla;
    }

    public String getSifOglasivaca() {
        return sifOglasivaca;
    }

    public void setSifOglasivaca(String sifOglasivaca) {
        this.sifOglasivaca = sifOglasivaca;
    }*/
}
