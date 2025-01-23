package com.progi.progi.service;

import com.progi.progi.model.Locatedat;
import com.progi.progi.repository.LocatedatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocatedatService {

    @Autowired
    private LocatedatRepository locatedatRepository;

    public Locatedat get(int id) { return locatedatRepository.findById(id).orElse(null); }
    public List<Locatedat> getAll() { return (List<Locatedat>) locatedatRepository.findAll(); }
    public List<Locatedat> getByLocation(int id) { return locatedatRepository.findByLocationId(id); }
    public List<Locatedat> getByLocationAndArticleID(int locationID, int articleID) {
        return locatedatRepository.findByLocationAndArticleId(locationID, articleID);
    }
    public Locatedat add(Locatedat locatedat) { return locatedatRepository.save(locatedat); }
    public List<Locatedat> getByArticleID(int articleID) { return locatedatRepository.findByArticleId(articleID); }
    public void delete(int id) { locatedatRepository.deleteById(id); }
}
