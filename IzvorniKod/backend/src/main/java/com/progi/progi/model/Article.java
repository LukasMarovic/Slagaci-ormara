package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "ARTICLE")
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLEID", nullable = false)
    private Integer id;

    @Column(name = "ARTICLENAME", length = 250)
    private String articlename;

    @Column(name = "ARTICLEPICTURE", length = 250)
    private String articlepicture;

    @Column(name = "CATEGORY", length = 50)
    private String category;

    @Column(name = "SEASONALITY", length = 50)
    private String seasonality;

    @Column(name = "FORMALITY", length = 50)
    private String formality;

    @Column(name = "MAINCOLOR", length = 20)
    private String maincolor;

    @Column(name = "SECONDARYCOLOR", length = 20)
    private String secondarycolor;

    @Column(name = "AVAILABILITY", length = 50)
    private String availability;

    @Column(name = "PRICE", precision = 7, scale = 2)
    private BigDecimal price;

    @Column(name = "USERID")
    private Integer userid;

    public Article() {}
    public Article(Article article) {
        this.id = article.getId();
        this.articlename = article.getArticlename();
        this.articlepicture = article.getArticlepicture();
        this.category = article.getCategory();
        this.seasonality = article.getSeasonality();
        this.formality = article.getFormality();
        this.maincolor = article.getMaincolor();
        this.secondarycolor = article.getSecondarycolor();
        this.availability = article.getAvailability();
        this.price = article.getPrice();
        this.userid = article.getUserid();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getArticlepicture() {
        return articlepicture;
    }

    public void setArticlepicture(String articlepicture) {
        this.articlepicture = articlepicture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(String seasonality) {
        this.seasonality = seasonality;
    }

    public String getFormality() {
        return formality;
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }

    public String getMaincolor() {
        return maincolor;
    }

    public void setMaincolor(String maincolor) {
        this.maincolor = maincolor;
    }

    public String getSecondarycolor() {
        return secondarycolor;
    }

    public void setSecondarycolor(String secondarycolor) {
        this.secondarycolor = secondarycolor;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}