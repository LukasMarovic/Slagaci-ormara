package com.progi.progi.web;

import com.progi.progi.model.Users;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> authenticateUser(@RequestBody Users user, HttpServletResponse response) {

        List<Users> users = userService.getByEmail(user.getEmail());
        if (!users.isEmpty()) {
            if (users.getFirst().getPassword().equals(user.getPassword())) {
                Cookie sessionCookie = new Cookie("SESSION_ID", UUID.randomUUID().toString());
                sessionCookie.setAttribute("username", user.getEmail());
                sessionCookie.setHttpOnly(false);
                sessionCookie.setSecure(true);
                sessionCookie.setPath("/");
                sessionCookie.setMaxAge(24 * 60 * 60);

                response.addCookie(sessionCookie);
                return ResponseEntity.ok("Logged in");
                //return new ResponseEntity<>("Users signed-in successfully!.", HttpStatus.OK);
            } else {

                return new ResponseEntity<>("Users signed-in failed.", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Users not found!", HttpStatus.NOT_FOUND);
    }
}
