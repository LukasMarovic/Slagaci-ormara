package com.progi.progi.service;

import com.progi.progi.model.User;
import com.progi.progi.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        validatePassword(user.getLozinka());
        return userRepository.save(user);
    }

    public User add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        validatePassword(user.getLozinka());
        return userRepository.save(user);
    }

    public static Integer getUserFromSession(HttpSession session) {
        if (session != null) {
            return (Integer) session.getAttribute("sif_korisnika");
        }
        return null;
    }

    private void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
    }
}
