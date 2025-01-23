package com.progi.progi.service;

import com.progi.progi.model.*;
import com.progi.progi.repository.ArticleRepository;
import com.progi.progi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private FootwearService footwearService;

    @Autowired
    @Lazy
    private ClothesService clothesService;

    @Autowired
    private LocatedatService locatedatService;

    public Article get(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article add(String nazivArtikla, String slikaArtikla, String opcaKategorija, String kategorijaGoddoba, String kategorijaLezernosti, String glavnaBoja, String sporednaBoja, String stanjeArtikla, Integer sifKorisnika, String type) {
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
        Article newArticle = articleRepository.save(article);
        if (type.equals("footwear")) {
            Footwear footwear = new Footwear();
            footwear.setId(article.getId());
            footwearService.add(footwear);
        } else if (type.equals("clothes")) {
            Clothes clothes = new Clothes();
            clothes.setId(article.getId());
            clothesService.add(clothes);
        }
        return newArticle;
    }

    public Article add(Article article, String type) {
        Article newArticle = articleRepository.save(article);
        if (type.equals("footwear")) {
            Footwear footwear = new Footwear();
            footwear.setId(article.getId());
            footwearService.add(footwear);
        } else if (type.equals("clothes")) {
            Clothes clothes = new Clothes();
            clothes.setId(article.getId());
            clothesService.add(clothes);
        }
        return newArticle;
    }

    public boolean remove(Integer id) {
        Footwear footwear = footwearService.getById(id);
        Clothes clothes = clothesService.getById(id);
        List<Locatedat> locatedatList = locatedatService.getByArticleID(id);
        if (!locatedatList.isEmpty()) {
            for (Locatedat locatedat : locatedatList) {
                locatedatService.delete(locatedat.getId());
            }
        }
        if (footwear != null) {
            footwearService.delete(id);
        }
        if (clothes != null) {
            clothesService.delete(id);
        }
        articleRepository.deleteById(id);
        return true;
    }

    public Iterable<Article> getAll() {
        return articleRepository.findAll();
    }

    public List<Article> getByUserID(Integer id) {
        return articleRepository.getByUserID(id);
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

        return randomArticles
                .stream()
                .collect(Collectors
                        .toMap(key ->
                        key, key -> userRepository.findById(key.getUserid())
                        .map(Users::getUsername).orElse("Unknown user")
                        ));
    }
}
