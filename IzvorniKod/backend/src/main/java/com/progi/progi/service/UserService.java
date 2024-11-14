package com.progi.progi.service;

import com.progi.progi.model.User;
import com.progi.progi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getByEmail(String email) { return userRepository.findByEmail(email); }

    public User add(String ime, String email, String lozinka, String geolokacija) {
        User user = new User();
        user.setImeKorisnika(ime);
        user.setEmail(email);
        user.setLozinka(lozinka);
        user.setGeolokacija(geolokacija);
        return userRepository.save(user);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
