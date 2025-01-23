package com.progi.progi.service;

import com.progi.progi.model.Article;
import com.progi.progi.model.Footwear;
import com.progi.progi.model.Scrapper;
import com.progi.progi.repository.FootwearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootwearService {

    @Autowired
    private FootwearRepository footwearRepository;
    @Autowired
    private ArticleService articleService;

    public Footwear add(Footwear footwear) {
        Scrapper scrapper = new Scrapper();
        Article article = articleService.get(footwear.getId());
        String coverage = scrapper.getCoverage(article.getCategory(), List.of(article.getArticlename().toLowerCase().split("")));
        footwear.setOpenness(coverage);
        return footwearRepository.save(footwear);
    }
    public Footwear getById(int id) { return footwearRepository.findById(id).orElse(null); }
    public List<Footwear> getAll() { return (List<Footwear>) footwearRepository.findAll(); }
    public void delete(int id) {
        articleService.remove(id);
    }
}