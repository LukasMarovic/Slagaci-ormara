package com.progi.progi.web;

import com.progi.progi.model.Article;
import com.progi.progi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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

    @GetMapping(value = "/getImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Integer id) throws IOException, URISyntaxException {
        return Files.readAllBytes(Path.of(Objects.requireNonNull(this.getClass().getResource("/static/images/" + id + ".jpg")).toURI()));
        // return Files.readAllBytes(new ClassPathResource("/static/images/" + id + ".jpg").getFile().toPath());
    }

    @PostMapping("/addArticle")
    public Article postArticle(@RequestBody Article article) {
        return articleService.add(article);
    }

    @DeleteMapping("/removeArticle/{id}")
    public boolean removeArticle(@PathVariable Integer id) {
        return articleService.remove(id);
    }
}
