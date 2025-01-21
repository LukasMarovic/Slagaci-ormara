package com.progi.progi.web;

import com.progi.progi.model.Registereduser;
import com.progi.progi.service.RegistereduserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RegistereduserController {

    @Autowired
    private RegistereduserService registereduserService;

    @GetMapping("/getRegistered/{id}")
    public Registereduser getRegisteredById(@PathVariable int id) { return registereduserService.getById(id); }

    @GetMapping("/getAllRegistered")
    public List<Registereduser> getAllRegistered(){ return registereduserService.getAll(); }

    @PostMapping
    public Registereduser createRegisteredUser(@RequestBody Registereduser registereduser) { return registereduserService.add(registereduser); }

    @DeleteMapping("/deleteRegistered/{id}")
    public void deleteRegisteredById(@PathVariable int id) { registereduserService.delete(id); }

}
