package com.progi.progi.web;

import com.progi.progi.model.Ormar;
import com.progi.progi.service.OrmarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://closetly-721y.onrender.com")
public class OrmarController {

    @Autowired
    private OrmarService ormarService;

    @GetMapping("/getOrmar/{id}")
    public Ormar getOrmar(@PathVariable int id) {
        return ormarService.get(id);
    }

    @PostMapping("/addOrmar/{naziv}")
    public Ormar addOrmar(@PathVariable String naziv) {
        return ormarService.add(naziv);
    }

}
