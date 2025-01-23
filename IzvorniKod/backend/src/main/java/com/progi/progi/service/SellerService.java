package com.progi.progi.service;

import com.progi.progi.model.Seller;
import com.progi.progi.model.UsersGenerator;
import com.progi.progi.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserService userService;

    public Seller getById(int id) { return sellerRepository.findById(id).orElse(null); }
    public List<Seller> getAll() { return (List<Seller>) sellerRepository.findAll(); }
    public Seller add(Seller seller) {
        UsersGenerator generator = new UsersGenerator();
        String logo = generator.getLogo();
        seller.setLogo(logo);
        return sellerRepository.save(seller);
    }
    public void delete(int id) {
        userService.delete(id);
    }
}
