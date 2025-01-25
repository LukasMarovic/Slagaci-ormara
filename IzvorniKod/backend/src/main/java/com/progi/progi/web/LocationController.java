package com.progi.progi.web;

import ch.qos.logback.core.model.Model;
import com.progi.progi.model.Closet;
import com.progi.progi.model.Location;
import com.progi.progi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/getLocation/{id}")
    public Location getLocation(@PathVariable int id) {
        return locationService.get(id);
    }

    @GetMapping("/getLocations/{closetID}")
    public List<Location> getLocations(@PathVariable int closetID) {
        return locationService.getByClosetId(closetID);
    }

    @PostMapping("/addLocation")
    public Location addLocation(@RequestBody String type, @RequestBody int closetID) {
        Location location = new Location();
        location.setClosetid(closetID);
        location.setLocationtype(type);
        return locationService.add(location);
    }

    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable int id) {
        locationService.delete(id);
    }
}
