package com.progi.progi.service;

import com.progi.progi.model.Registereduser;
import com.progi.progi.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistereduserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private UserService userService;

    public Registereduser getById(int id) { return registeredUserRepository.findById(id).orElse(null); }
    public List<Registereduser> getAll() { return (List<Registereduser>) registeredUserRepository.findAll(); }
    public Registereduser add(Registereduser registereduser) { return registeredUserRepository.save(registereduser); }
    public void delete(int id) {
        registeredUserRepository.deleteById(id);
        userService.delete(id);
    }
}
