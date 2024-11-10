package com.progi.progi.service;

import com.progi.progi.model.Ormar;
import com.progi.progi.repository.OrmarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrmarService {

    @Autowired
    private OrmarRepository ormarRepository;

    public Ormar get(int id) {
        return ormarRepository.findById(id).orElse(null);
    }

    public Ormar add(String naziv) {
        Ormar ormar = new Ormar();
        ormar.setNazivOrmara(naziv);
        ormar.setSifKorisnika(null);
        return ormarRepository.save(ormar);
    }

    public boolean delete(int id) {
        Ormar ormar = ormarRepository.findById(id).orElse(null);
        ormarRepository.delete(ormar);
        return true;
    }
}
