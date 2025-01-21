package com.progi.progi.service;

import com.progi.progi.model.Users;
import com.progi.progi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users get(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<Users> getByEmail(String email) { return userRepository.findByEmail(email); }
    public List<Users> getAllUsers() { return (List<Users>) userRepository.findAll(); }

    public Users add(String ime, String email, String lozinka) {
        Users user = new Users();
        user.setUsername(ime);
        user.setEmail(email);
        user.setPassword(lozinka);
        return userRepository.save(user);
    }

    public Users add(Users user) {
        return userRepository.save(user);
    }

    public Users update(Users user) { return userRepository.save(user); }
    public void delete(int id) { userRepository.deleteById(id); }
}
