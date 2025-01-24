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
    public Location findLocation(Integer id, String locationType, Integer locationNumber) {
        return locationRepository.findLocation(id, locationType, locationNumber);
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
            Location location = new Location(ormar.getId(), "drawer", i);
            locationRepository.save(location);
        }
        for (int i = 0; i < numberOfShelves; i++) {
            Location location = new Location(ormar.getId(), "shelf", i);
            locationRepository.save(location);
        }
        for (int i = 0; i < numberOfHangers; i++) {
            Location location = new Location(ormar.getId(), "hanger", i);
            locationRepository.save(location);
        }
    }
}
