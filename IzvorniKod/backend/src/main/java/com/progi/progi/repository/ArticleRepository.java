package com.progi.progi.repository;

import com.progi.progi.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query("SELECT a FROM Article a WHERE a.userid = :userID")
    public List<Article> getByUserID(@Param("userID") Integer userID);
}
