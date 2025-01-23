package com.progi.progi.web;

import com.progi.progi.model.Closet;
import com.progi.progi.model.Location;
import com.progi.progi.service.LocationService;
import com.progi.progi.service.OrmarService;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class OrmarController {

    @Autowired
    private OrmarService ormarService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/getOrmar/{id}")
    public Closet getOrmar(@PathVariable int id) {
        return ormarService.get(id);
    }

    @PostMapping("/addOrmar")
    public Closet addOrmar(@RequestBody Map<String, String> payload, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Closet ormar = new Closet();
            ormar.setClosetname(payload.get("name"));
            ormar.setUserid(UserService.getUserFromSession(session));
            Closet ormarNovi = ormarService.add(ormar);
            locationService.addLocations(ormarNovi, Integer.parseInt(payload.get("numberOfDrawers")), Integer.parseInt(payload.get("numberOfShelves")), Integer.parseInt(payload.get("numberOfHangers")));
            return ormarNovi;
        }
        return null;
    }

    @GetMapping("/getUserOrmari")
    public HashMap<String, List<Location>> getUserOrmari(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return ormarService.getByUser((Integer) session.getAttribute("sif_korisnika"));
        }
        return null;
    }
}
