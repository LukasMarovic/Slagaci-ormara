package com.progi.progi.repository;

import com.progi.progi.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query("SELECT a FROM Article a WHERE a.userid = :userID")
    public List<Article> getByUserID(@Param("userID") Integer userID);

    @Query("SELECT a FROM Article a WHERE a.articlename LIKE CONCAT(:txt, '%')")
    public List<Article> getByText(@Param("txt") String txt);

    @Query("SELECT a FROM Article a WHERE (a.articlename LIKE CONCAT(:txt, '%')) AND (a.userid = :userID)")
    public List<Article> getByUserIDAndText(@Param("userID") Integer userID, @Param("txt") String txt);

    @Query("SELECT a FROM Article a INNER JOIN Seller u ON a.userid = u.id")
    public List<Article> getAllSellerArticles();
}
