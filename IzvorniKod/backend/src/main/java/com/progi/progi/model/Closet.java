package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CLOSET")
public class Closet {
    @Id
    @Column(name = "CLOSETID", nullable = false)
    private Integer id;

    @Column(name = "CLOSETNAME", length = 50)
    private String closetname;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USERID")
    private com.progi.progi.model.Users userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClosetname() {
        return closetname;
    }

    public void setClosetname(String closetname) {
        this.closetname = closetname;
    }

    public com.progi.progi.model.Users getUserid() {
        return userid;
    }

    public void setUserid(com.progi.progi.model.Users userid) {
        this.userid = userid;
    }

}