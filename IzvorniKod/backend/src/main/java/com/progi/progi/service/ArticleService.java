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
        if (type != null) {
            if (type.equals("footwear")) {
                Footwear footwear = new Footwear();
                footwear.setId(article.getId());
                footwearService.add(footwear);
            } else if (type.equals("clothes")) {
                Clothes clothes = new Clothes();
                clothes.setId(article.getId());
                clothesService.add(clothes);
            }
        }
        return newArticle;
    }

    public Article add(Article article) {
        return articleRepository.save(article);
    }

    public boolean remove(Integer id) {
        if (articleRepository.existsById(id))
        {
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
        else return false;
    }

    public Iterable<Article> getAll() {
        return articleRepository.findAll();
    }

    public List<Article> getByUserID(Integer id) {
        return articleRepository.getByUserID(id);
    }

    public List<Article> startsWith(String text) {
        return articleRepository.getByText(text);
    }

    public List<Article> startsWithAndBelongsTo(Integer userID, String text) {
        return articleRepository.getByUserIDAndText(userID, text);
    }

    public Map<Article, String> getFeatured() {
        List<Article> allArticles = (List<Article>) articleRepository.findAll();

        // Provjera postoji li barem jedan artikl
        if (allArticles.isEmpty()) {
            return Collections.emptyMap();
        }

        // Shuffle liste kako bi nasumično rasporedili artikle
        Collections.shuffle(allArticles, new Random());

        // Odabir prvih 8 artikala ili manje ako ih nema dovoljno
        List<Article> randomArticles = allArticles.stream()
                .limit(8)
                .collect(Collectors.toList());

        // Kreiranje mape artikla i korisničkog imena
        return randomArticles.stream()
                .collect(Collectors.toMap(
                        article -> article,
                        article -> userRepository.findById(article.getUserid())
                                .map(Users::getUsername)
                                .orElse("Unknown user")
                ));
    }

    public List<Article> getAllSellerArticles() {
        List<Article> articles = articleRepository.getAllSellerArticles();
        if (articles.size() > 4) {
            articles = articles.subList(0,4);
        }
        return articles;
    }
}