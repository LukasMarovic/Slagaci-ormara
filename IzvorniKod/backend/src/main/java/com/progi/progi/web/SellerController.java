package com.progi.progi.web;

import com.progi.progi.model.Seller;
import com.progi.progi.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/getSeller/{id}")
    public Seller getSeller(@PathVariable int id) { return sellerService.getById(id); }

    @GetMapping("/getAllSellers")
    public List<Seller> getAllSellers() { return sellerService.getAll(); }

    @PostMapping("/addSeller")
    public Seller addSeller(@RequestBody Seller seller) { return sellerService.add(seller); }

    @DeleteMapping("/removeSeller/{id}")
    public void removeSeller(@PathVariable int id) { sellerService.delete(id); }
}
