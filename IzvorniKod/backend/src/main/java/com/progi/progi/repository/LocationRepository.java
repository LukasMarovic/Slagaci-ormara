package com.progi.progi.repository;

import com.progi.progi.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    @Query("SELECT l FROM Location l WHERE l.id.closetid =:id")
    public List<Location> findByClosetID(@Param("id") Integer id);
}
