package com.progi.progi.service;

import com.progi.progi.model.*;
import com.progi.progi.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrmarService ormarService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    @Lazy
    private RegistereduserService registereduserService;
    @Autowired
    @Lazy
    private SellerService sellerService;

    public Users get(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<Users> getByEmail(String email) { return userRepository.findByEmail(email); }
    public List<Users> getAllUsers() { return (List<Users>) userRepository.findAll(); }

//    public Users add(String ime, String email, String lozinka, String role) {
//        Users user = new Users();
//        user.setUsername(ime);
//        user.setEmail(email);
//        user.setPassword(lozinka);
//        Users newUser = userRepository.save(user);
//        if (role.equals("seller")) {
//            Seller seller = new Seller();
//            seller.setId(newUser.getId());
//            sellerService.add(seller);
//        }
//        else if (role.equals("registereduser")) {
//            Registereduser registereduser = new Registereduser();
//            registereduser.setId(newUser.getId());
//            registereduserService.add(registereduser);
//        }
//        return newUser;
//    }

    public Users add(Users user, String role) {
        List<Users> tmp_user = userRepository.findByEmail(user.getEmail());
        if (!tmp_user.isEmpty()) {
            return tmp_user.get(0);
        }
        Users newUser = userRepository.save(user);
        if (role.equals("seller")) {
            Seller seller = new Seller();
            seller.setId(newUser.getId());
            sellerService.add(seller);
        }
        else if (role.equals("registereduser")) {
            Registereduser registereduser = new Registereduser();
            registereduser.setId(newUser.getId());
            registereduserService.add(registereduser);
        }
        return newUser;
    }

    public Users addRegistered(Users user, String geolocation) {
        List<Users> tmp_user = userRepository.findByEmail(user.getEmail());
        if (!tmp_user.isEmpty()) {
            return tmp_user.get(0);
        }
        Users newUser = userRepository.save(user);
        Registereduser registereduser = new Registereduser();
        registereduser.setId(newUser.getId());
        registereduser.setGeolocation(geolocation);
        registereduserService.add(registereduser);
        return newUser;
    }

    public Users addSeller(Users user, String image) {
        List<Users> tmp_user = userRepository.findByEmail(user.getEmail());
        if (!tmp_user.isEmpty()) {
            return tmp_user.get(0);
        }
        Users newUser = userRepository.save(user);
        Seller seller = new Seller();
        seller.setId(newUser.getId());
        sellerService.add(seller, image);
        return newUser;
    }

    public void delete(int id) {
        List<Closet> closets = ormarService.getByUserID(id);
        List<Article> articles = articleService.getByUserID(id);
        if (!closets.isEmpty()) {
            for (Closet closet : closets) {
                ormarService.delete(closet.getId());
            }
        }
        if (!articles.isEmpty()) {
            for (Article article : articles) {
                articleService.remove(article.getId());
            }
        }
        Seller seller = sellerService.getById(id);
        Registereduser registereduser = registereduserService.getById(id);
        if (registereduser != null) {
            registereduserService.delete(id);
        }
        if (seller != null) {
            sellerService.delete(id);
        }
        userRepository.deleteById(id);
    }
    public static Integer getUserFromSession(HttpSession session) {
        if (session != null) {
            return (Integer) session.getAttribute("sif_korisnika");
        }
        return null;
    }
}
