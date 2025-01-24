package com.progi.progi.repository;

import com.progi.progi.model.Locatedat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocatedatRepository extends CrudRepository<Locatedat, Integer> {
    @Query("SELECT la FROM Locatedat la WHERE la.locationid = :id")
    public List<Locatedat> findByLocationId(@Param("id") Integer id);

    @Query("SELECT la FROM Locatedat la WHERE la.locationid = :locationID AND la.articleid = :articleID")
    public List<Locatedat> findByLocationAndArticleId(@Param("locationID") Integer locationID, @Param("articleID") Integer articleID);

    @Query("SELECT la FROM Locatedat la WHERE la.articleid = :articleID")
    public List<Locatedat> findByArticleId(@Param("articleID") Integer articleID);
}
