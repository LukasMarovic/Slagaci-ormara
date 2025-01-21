package com.progi.progi.service;

import com.progi.progi.model.Clothes;
import com.progi.progi.repository.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {

    @Autowired
    private ClothesRepository clothesRepository;

    public Clothes getById(int id) { return clothesRepository.findById(id).orElse(null); }
    public List<Clothes> getAll() { return (List<Clothes>) clothesRepository.findAll(); }
    public Clothes add(Clothes clothes) { return clothesRepository.save(clothes); }
    public Clothes update(Clothes clothes) { return clothesRepository.save(clothes); }
    public void delete(int id) { clothesRepository.deleteById(id); }
}
