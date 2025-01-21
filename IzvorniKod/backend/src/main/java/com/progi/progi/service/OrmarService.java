package com.progi.progi.service;

import com.progi.progi.model.Closet;
import com.progi.progi.repository.OrmarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrmarService {

    @Autowired
    private OrmarRepository ormarRepository;

    public Closet get(int id) {
        return ormarRepository.findById(id).orElse(null);
    }

    public Closet add(String naziv) {
        Closet closet = new Closet();
        closet.setClosetname(naziv);
        closet.setUserid(null);
        return ormarRepository.save(closet);
    }

    public boolean delete(int id) {
        Closet closet = ormarRepository.findById(id).orElse(null);
        ormarRepository.delete(closet);
        return true;
    }
}
