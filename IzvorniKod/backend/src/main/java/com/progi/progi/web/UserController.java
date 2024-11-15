package com.progi.progi.web;

import com.progi.progi.model.User;
import com.progi.progi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://closetly-721y.onrender.com")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id) { return userService.get(id); }

    @GetMapping("/sign-in/{email}")
    public List<User> getUsers(@PathVariable String email) { return userService.getByEmail(email); }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) { return userService.add(user); }
}
