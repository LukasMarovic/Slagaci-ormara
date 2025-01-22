package com.progi.progi.web;

import com.progi.progi.model.Location;
import com.progi.progi.model.Ormar;
import com.progi.progi.service.OrmarService;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://closetly-721y.onrender.com")
public class OrmarController {

    @Autowired
    private OrmarService ormarService;

    @GetMapping("/getOrmar/{id}")
    public Ormar getOrmar(@PathVariable int id) {
        return ormarService.get(id);
    }

    @PostMapping("/addOrmar")
    public Ormar addOrmar(@RequestBody Map<String, String> payload, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Ormar ormar = new Ormar();
            ormar.setNazivOrmara(payload.get("name"));
            ormar.setSifKorisnika(UserService.getUserFromSession(session));
            Ormar ormarNovi = ormarService.add(ormar);
            ormarService.addLocations(ormarNovi, Integer.parseInt(payload.get("numberOfDrawers")), Integer.parseInt(payload.get("numberOfShelves")), Integer.parseInt(payload.get("numberOfHangers")));
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
