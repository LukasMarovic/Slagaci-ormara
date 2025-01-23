package com.progi.progi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "LOCATEDAT")
public class Locatedat {
    @EmbeddedId
    private LocatedatKey id;

    public Locatedat() {
    }

    public Locatedat(LocatedatKey id) {
        this.id = id;
    }

    public LocatedatKey getId() {
        return id;
    }

    public void setId(LocatedatKey id) {
        this.id = id;
    }
}