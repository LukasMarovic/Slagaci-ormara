package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "CLOTHES")
public class Clothes{
    @Id
    @Column(name = "ARTICLEID", nullable = false)
    private Integer id;

    public Clothes() {}
    public Clothes(Clothes clothes) {
        this.id = clothes.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}