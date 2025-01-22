package com.progi.progi.service;

import com.progi.progi.model.Closet;
import com.progi.progi.model.Location;
import com.progi.progi.repository.OrmarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrmarService {

    @Autowired
    private OrmarRepository ormarRepository;
    @Autowired
    private LocationService locationService;

    public Closet get(int id) {
        return ormarRepository.findById(id).orElse(null);
    }

    public List<Closet> getAll() {
        return (List<Closet>) ormarRepository.findAll();
    }

    public Closet add(String naziv) {
        Closet closet = new Closet();
        closet.setClosetname(naziv);
        closet.setUserid(null);
        return ormarRepository.save(closet);
    }

    public boolean delete(int id) {
        Closet closet = ormarRepository.findById(id).orElse(null);
        List<Location> locations = locationService.getByClosetId(closet.getId());
        for (Location location : locations) {
            locationService.delete(location.getId());
        }
        ormarRepository.delete(closet);
        return true;
    }
}
