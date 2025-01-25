package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CLOSET")
public class Closet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLOSETID", nullable = false)
    private Integer id;

    @Column(name = "CLOSETNAME", length = 50)
    private String closetname;

    @Column(name = "USERID")
    private Integer userid;

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}