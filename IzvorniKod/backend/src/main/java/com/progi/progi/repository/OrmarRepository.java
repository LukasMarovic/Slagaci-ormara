package com.progi.progi.repository;

import com.progi.progi.model.Ormar;
import com.progi.progi.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrmarRepository extends CrudRepository<Ormar, Integer> {
    @Query("SELECT o FROM Ormar o WHERE o.sifKorisnika = :sifKorisnika")
    public List<Ormar> findByUser(@Param("sifKorisnika") Integer sifKorisnika);
}
