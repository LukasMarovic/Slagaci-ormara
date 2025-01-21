package com.progi.progi.web;

import com.progi.progi.model.Footwear;
import com.progi.progi.service.FootwearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FootwearController {

    @Autowired
    public FootwearService footwearService;

    @GetMapping("/getFootwear/{id}")
    public Footwear getFootwear(@PathVariable int id) { return footwearService.getById(id); }

    @GetMapping("/getAllFootwear")
    public List<Footwear> getAllFootwear() { return footwearService.getAll(); }

    @PostMapping("/addFootwear")
    public Footwear addFootwear(@RequestBody Footwear footwear) { return footwearService.add(footwear); }

    @DeleteMapping("/removeFootwear/{id}")
    public void removeFootwear(@PathVariable int id) { footwearService.delete(id); }
}
