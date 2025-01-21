package com.progi.progi.web;

import com.progi.progi.model.Clothes;
import com.progi.progi.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class ClothesController {

    @Autowired
    private ClothesService clothesService;

    @GetMapping("/getClothes/{id}")
    public Clothes getClothes(@PathVariable int id) { return clothesService.getById(id); }

    @GetMapping("/getAllClothes")
    public List<Clothes> getAllClothes() { return clothesService.getAll(); }

    @PostMapping("/addClothes")
    public Clothes add(@RequestBody Clothes clothes) { return clothesService.add(clothes); }

    @DeleteMapping("/deleteClothes/{id}")
    public void delete(@PathVariable int id) { clothesService.delete(id); }
}
