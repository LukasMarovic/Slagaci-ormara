package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "FOOTWEAR")
public class Footwear{
    @Id
    @Column(name = "ARTICLEID", nullable = false)
    private Integer id;

    @Column(name = "OPENNESS", length = 50)
    private String openness;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenness() {
        return openness;
    }

    public void setOpenness(String openness) {
        this.openness = openness;
    }

}