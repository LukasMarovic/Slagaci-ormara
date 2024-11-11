package com.progi.progi.web;

import com.progi.progi.model.Article;
import com.progi.progi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //vraća 8 nasumičnih artikala iz baze
    @GetMapping("/getFeatured")
    public Iterable<Article> getArticles() {
        return articleService.getFeatured();
    }

    @GetMapping("/getArticle/{id}")
    public Article getArticle(@PathVariable Integer id) {
        return articleService.get(id);
    }

    @PostMapping("/addArticle/{naziv}")
    public Article postArticle(@PathVariable String naziv) {
        return articleService.add(naziv);
    }

    @DeleteMapping("/removeArticle/{id}")
    public boolean removeArticle(@PathVariable Integer id) {
        return articleService.remove(id);
    }
}
