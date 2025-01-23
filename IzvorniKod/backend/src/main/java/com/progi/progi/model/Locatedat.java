package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATEDAT")
public class Locatedat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATEDATID", nullable = false)
    private Integer id;

    @Column(name = "ARTICLEID")
    private Integer articleid;

    @Column(name = "LOCATIONID")
    private Integer locationid;

    @Column(name = "CLOSETID")
    private Integer closetid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getClosetid() {
        return closetid;
    }

    public void setClosetid(Integer closetid) {
        this.closetid = closetid;
    }

}