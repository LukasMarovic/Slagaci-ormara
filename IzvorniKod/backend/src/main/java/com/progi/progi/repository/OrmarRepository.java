package com.progi.progi.repository;

import com.progi.progi.model.Closet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrmarRepository extends CrudRepository<Closet, Integer> {
    @Query("SELECT o FROM Closet o WHERE o.userid = :id")
    public List<Closet> findByUserID(@Param("id") Integer id);
}
