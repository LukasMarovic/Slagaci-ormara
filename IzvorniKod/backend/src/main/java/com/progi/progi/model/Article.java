package com.progi.progi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ARTIKL")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sifArtikla;
    private String nazivArtikla;

    @Lob
    private byte[] slikaArtikla;
    private String opcaKategorija;
    private String kategorijaGoddoba;
    private String kategorijaLezernosti;
    private String glavnaBoja;
    private String sporednaBoja;
    private String stanjeArtikla;
    private Integer sifKorisnika;

    public Article() {
    }

    public Article(Integer sifArtikla, String nazivArtikla, byte[] slikaArtikla, String opcaKategorija, String kategorijaGoddoba, String kategorijaLezernosti, String glavnaBoja, String sporednaBoja, String stanjeArtikla, Integer sifKorisnika) {
        this.sifArtikla = sifArtikla;
        this.nazivArtikla = nazivArtikla;
        this.slikaArtikla = slikaArtikla;
        this.opcaKategorija = opcaKategorija;
        this.kategorijaGoddoba = kategorijaGoddoba;
        this.kategorijaLezernosti = kategorijaLezernosti;
        this.glavnaBoja = glavnaBoja;
        this.sporednaBoja = sporednaBoja;
        this.stanjeArtikla = stanjeArtikla;
        this.sifKorisnika = sifKorisnika;
    }

    public Integer getSifArtikla() {
        return sifArtikla;
    }

    public void setSifArtikla(Integer sifArtikla) {
        this.sifArtikla = sifArtikla;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public byte[] getSlikaArtikla() {
        return slikaArtikla;
    }

    public void setSlikaArtikla(byte[] slikaArtikla) {
        this.slikaArtikla = slikaArtikla;
    }

    public String getOpcaKategorija() {
        return opcaKategorija;
    }

    public void setOpcaKategorija(String opcaKategorija) {
        this.opcaKategorija = opcaKategorija;
    }

    public String getKategorijaGoddoba() {
        return kategorijaGoddoba;
    }

    public void setKategorijaGoddoba(String kategorijaGoddoba) {
        this.kategorijaGoddoba = kategorijaGoddoba;
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

    public Integer getSifKorisnika() {
        return sifKorisnika;
    }

    public void setSifKorisnika(Integer sifKorisnika) {
        this.sifKorisnika = sifKorisnika;
    }
}
