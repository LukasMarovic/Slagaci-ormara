package com.progi.progi.service;

import com.progi.progi.model.Closet;
import com.progi.progi.model.Locatedat;
import com.progi.progi.model.Location;
import com.progi.progi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private LocatedatService locatedatService;

    public Location add(Location location) { return locationRepository.save(location); }
    public List<Location> getAll() { return (List<Location>) locationRepository.findAll(); }
    public Location get(int id) { return locationRepository.findById(id).orElse(null); }
    public List<Location> getByClosetId(int id) { return locationRepository.findByClosetID(id); }
    public Location findLocation(Integer id, String locationtype, Integer locationnumber) {
        return locationRepository.findLocation(id, locationtype, locationnumber);
    }
    public void delete(int id) {
        List<Locatedat> locatedats = locatedatService.getByLocation(id);
        for (Locatedat locatedat : locatedats) {
            locatedatService.delete(locatedat.getId());
        }
        locationRepository.deleteById(id);
    }

    public void addLocations(Closet ormar, Integer numberOfDrawers, Integer numberOfShelves, Integer numberOfHangers) {
        for (int i = 0; i < numberOfDrawers; i++) {
            locationRepository.save(new Location(null, ormar.getId(), "drawer", i+1));
        }
        for (int i = 0; i < numberOfShelves; i++) {
            locationRepository.save(new Location(null, ormar.getId(), "shelf", i+1));
        }
        for (int i = 0; i < numberOfHangers; i++) {
            locationRepository.save(new Location(null, ormar.getId(), "hanger", i+1));
        }
    }
}
