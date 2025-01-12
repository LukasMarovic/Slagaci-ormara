package com.progi.progi.web;

import com.progi.progi.model.User;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/user")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = session.getAttribute("username").toString();
            return ResponseEntity.ok(username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in!");
    }
}
