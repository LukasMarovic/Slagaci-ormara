package com.progi.progi.web;

import com.progi.progi.model.Article;
import com.progi.progi.model.Locatedat;
import com.progi.progi.service.ArticleService;
import com.progi.progi.service.LocatedatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocatedatController {
    @Autowired
    private LocatedatService locatedatService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getLocatedAt/{id}")
    public Locatedat getLocatedat(@PathVariable int id) {
        return locatedatService.get(id);
    }

    @GetMapping("/getAllArticlesLocatedat/{locationId}")
    public List<Article> getAllArticlesLocatedat(@PathVariable Integer locationId) {
        List<Locatedat> locatedats = locatedatService.getByLocation(locationId);
        List<Article> articles = new ArrayList<>();
        for (Locatedat locatedat : locatedats) {
            Article article = articleService.get(locatedat.getId());
            articles.add(article);
        }
        return articles;
    }

    @PostMapping("/addArticleToLocation/{locationID}")
    public void addArticleToLocation(@PathVariable int locationID, @RequestBody int articleID) {
        Locatedat locatedat = new Locatedat();
        locatedat.setArticleid(articleID);
        locatedat.setLocationid(locationID);
        locatedatService.save(locatedat);
    }

    @DeleteMapping("/deleteArticleFromLocation/{locationID}")
    public void deleteArticleFromLocation(@PathVariable int locationID, @RequestBody int articleID) {
        List<Locatedat> locatedats = locatedatService.getByLocationAndArticleID(locationID, articleID);
        if (locatedats.size() > 0) {
            Locatedat locatedat = locatedats.get(0);
            locatedatService.delete(locatedat.getId());
        }
    }
}
