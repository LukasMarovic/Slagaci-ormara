package com.progi.progi.service;

import com.progi.progi.model.Article;
import com.progi.progi.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserService userService;

    public Article get(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article add(String nazivArtikla, String slikaArtikla, String opcaKategorija, String kategorijaGoddoba, String kategorijaLezernosti, String glavnaBoja, String sporednaBoja, String stanjeArtikla, Integer sifKorisnika) {
        Article article = new Article();
        article.setArticlename(nazivArtikla);
        article.setArticlepicture(slikaArtikla);
        article.setCategory(opcaKategorija);
        article.setSeasonality(kategorijaGoddoba);
        article.setFormality(kategorijaLezernosti);
        article.setMaincolor(glavnaBoja);
        article.setSecondarycolor(sporednaBoja);
        article.setAvailability(stanjeArtikla);
        article.setId(sifKorisnika);
        return articleRepository.save(article);
    }

    public Article add(Article article) {
        return articleRepository.save(article);
    }

    public boolean remove(Integer id) {
        articleRepository.deleteById(id);
        return true;
    }

    public Iterable<Article> getAll() {
        return articleRepository.findAll();
    }

    public Map<Article, String> getFeatured() {
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

        return randomArticles.stream().collect(Collectors.toMap(key -> key, key -> userService.get(key.getUserid()).getUsername()));
    }
}
