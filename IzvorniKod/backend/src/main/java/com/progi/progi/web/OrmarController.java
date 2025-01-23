package com.progi.progi.web;

import com.progi.progi.model.Closet;
import com.progi.progi.model.Location;
import com.progi.progi.service.OrmarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class OrmarController {

    @Autowired
    private OrmarService ormarService;

    @GetMapping("/getOrmar/{id}")
    public Closet getOrmar(@PathVariable int id) {
        return ormarService.get(id);
    }

    @PostMapping("/addOrmar/{naziv}")
    public Closet addOrmar(@PathVariable String naziv) {
        return ormarService.add(naziv);
    }

//    @GetMapping("/getUserOrmari")
//    public HashMap<String, List<Location>> getUserOrmari(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            return ormarService.getByUser((Integer) session.getAttribute("sif_korisnika"));
//        }
//        return null;
//    }
}
