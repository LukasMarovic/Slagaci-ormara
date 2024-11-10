package com.progi.progi.web;

import com.progi.progi.model.Ormar;
import com.progi.progi.service.OrmarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrmarController {

    @Autowired
    private OrmarService ormarService;

    @GetMapping("/getOrmar/{id}")
    public Ormar getOrmar(@PathVariable int id) {
        return ormarService.get(id);
    }

    @PostMapping("/addOrmar/{naziv}")
    public Ormar addOrmar(@RequestBody String naziv) {
        return ormarService.add(naziv);
    }

}
