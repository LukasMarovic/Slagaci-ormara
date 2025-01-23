package com.progi.progi.repository;

import com.progi.progi.model.Closet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrmarRepository extends CrudRepository<Closet, Integer> {
    @Query("SELECT c FROM Closet c WHERE c.userid = :userID")
    public List<Closet> findByUserID(@Param("userID") Integer userID);
}
