package com.progi.progi.service;

import com.progi.progi.model.Footwear;
import com.progi.progi.repository.FootwearRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FootwearService {

    @Autowired
    public FootwearRepository footwearRepository;

    public Footwear add(Footwear footwear) { return footwearRepository.save(footwear); }
    public Footwear getById(int id) { return footwearRepository.findById(id).orElse(null); }
    public List<Footwear> getAll() { return (List<Footwear>) footwearRepository.findAll(); }
    public void delete(int id) { footwearRepository.deleteById(id); }
}
