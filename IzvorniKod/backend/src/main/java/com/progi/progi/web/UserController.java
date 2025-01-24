package com.progi.progi.web;

import com.progi.progi.model.Users;
import com.progi.progi.service.CloudinaryService;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/getUser/{id}")
    public Users getUser(@PathVariable int id) { return userService.get(id); }

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() { return userService.getAllUsers(); }

    @GetMapping("/sign-in/{email}")
    public List<Users> getUsers(@PathVariable String email) { return userService.getByEmail(email); }

    @PostMapping("/addUser")
    public Users addUser(@RequestParam(value = "image", required = false) MultipartFile file, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("role") String role, @RequestParam(value = "geolocation", required = false) String geolocation) {
        Users user = new Users();
        user.setId(null);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        if (role.equals("seller")) {
            try {
                String image = cloudinaryService.uploadFile(file);
                return userService.addSeller(user, image);
            } catch (IOException e) {
                return null;
            }
        } else if (role.equals("registereduser")) {
            return userService.addRegistered(user, geolocation);
        }
        return null;
    }

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
