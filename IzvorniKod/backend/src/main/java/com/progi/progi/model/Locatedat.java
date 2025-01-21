package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATEDAT")
public class Locatedat {
    @Id
    @Column(name = "LOCATEDATID", nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ARTICLEID")
    private Article articleid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "LOCATIONID")
    private com.progi.progi.model.Location locationid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CLOSETID")
    private Closet closetid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticleid() {
        return articleid;
    }

    public void setArticleid(Article articleid) {
        this.articleid = articleid;
    }

    public com.progi.progi.model.Location getLocationid() {
        return locationid;
    }

    public void setLocationid(com.progi.progi.model.Location locationid) {
        this.locationid = locationid;
    }

    public Closet getClosetid() {
        return closetid;
    }

    public void setClosetid(Closet closetid) {
        this.closetid = closetid;
    }

}