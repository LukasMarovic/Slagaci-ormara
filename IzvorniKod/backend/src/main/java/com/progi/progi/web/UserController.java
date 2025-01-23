package com.progi.progi.web;

import com.progi.progi.model.Users;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{id}")
    public Users getUser(@PathVariable int id) { return userService.get(id); }

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() { return userService.getAllUsers(); }

    @GetMapping("/sign-in/{email}")
    public List<Users> getUsers(@PathVariable String email) { return userService.getByEmail(email); }

    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users user) { return userService.add(user); }

    @DeleteMapping("/deleteUser/{id}")
    public void removeUser(@PathVariable int id) { userService.delete(id); }

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
