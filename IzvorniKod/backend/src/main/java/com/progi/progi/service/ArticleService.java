package com.progi.progi.service;

import com.progi.progi.model.Article;
import com.progi.progi.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article get(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article add(String naziv) {
        Article article = new Article();
        article.setNazivArtikla(naziv);
        return articleRepository.save(article);
    }

    public boolean remove(Integer id) {
        articleRepository.deleteById(id);
        return true;
    }

    public Iterable<Article> getAll() {
        return articleRepository.findAll();
    }

    public List<Article> getFeatured() {
        List<Article> allArticles = (List<Article>) articleRepository.findAll();

        List<Article> randomArticles = new ArrayList<>();
        Random random = new Random();

        while (randomArticles.size() < 8 && allArticles.size() >= 8) {
            int randomIndex = random.nextInt(allArticles.size());
            Article randomArticle = allArticles.get(randomIndex);

            if (!randomArticles.contains(randomArticle)) {
                randomArticles.add(randomArticle);
            }
        }

        return randomArticles;
    }
}
