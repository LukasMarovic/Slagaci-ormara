package com.progi.progi.service;

import com.progi.progi.model.Location;
import com.progi.progi.model.Ormar;
import com.progi.progi.model.User;
import com.progi.progi.repository.LocationRepository;
import com.progi.progi.repository.OrmarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrmarService {

    @Autowired
    private OrmarRepository ormarRepository;

    @Autowired
    private LocationRepository locationrepository;

    public Ormar get(int id) {
        return ormarRepository.findById(id).orElse(null);
    }
    public HashMap<String, List<Location>> getByUser(Integer sifKorisnika) {
        List<Ormar> ormari = ormarRepository.findByUser(sifKorisnika);
        HashMap<String, List<Location>> map = new HashMap<>();
        for (Ormar ormar : ormari) {
            map.put(ormar.getNazivOrmara(), locationrepository.findByOrmar(ormar.getSifOrmara()));
        }
        return map;
    }

    public Ormar add(String naziv) {
        Ormar ormar = new Ormar();
        ormar.setNazivOrmara(naziv);
        ormar.setSifKorisnika(null);
        return ormarRepository.save(ormar);
    }

    public Ormar add(Ormar ormar) {
        return ormarRepository.save(ormar);
    }

    public void addLocations(Ormar ormar, Integer numberOfDrawers, Integer numberOfShelves, Integer numberOfHangers) {
        for (int i = 0; i < numberOfDrawers; i++) {
            locationrepository.save(new Location(i+1, ormar.getSifOrmara(), "drawer"));
        }
        for (int i = 0; i < numberOfShelves; i++) {
            locationrepository.save(new Location(i+1+numberOfDrawers, ormar.getSifOrmara(), "shelf"));
        }
        for (int i = 0; i < numberOfHangers; i++) {
            locationrepository.save(new Location(i+1+numberOfDrawers+numberOfShelves, ormar.getSifOrmara(), "hanger"));
        }
    }

    public boolean delete(int id) {
        Ormar ormar = ormarRepository.findById(id).orElse(null);
        ormarRepository.delete(ormar);
        return true;
    }
}
