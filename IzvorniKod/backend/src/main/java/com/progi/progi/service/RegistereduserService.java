package com.progi.progi.service;

import com.progi.progi.model.Registereduser;
import com.progi.progi.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegistereduserService {

    @Autowired
    public RegisteredUserRepository registeredUserRepository;

    public Registereduser getById(int id) { return registeredUserRepository.findById(id).orElse(null); }
    public List<Registereduser> getAll() { return (List<Registereduser>) registeredUserRepository.findAll(); }
    public void delete(int id) { registeredUserRepository.deleteById(id); }
    public Registereduser add(Registereduser registereduser) { return registeredUserRepository.save(registereduser); }
}
