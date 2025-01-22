package com.progi.progi.web;

import com.progi.progi.model.User;
import com.progi.progi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://closetly-721y.onrender.com")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> authenticateUser(@RequestBody User user, HttpServletRequest request) {

        List<User> users = userService.getByEmail(user.getEmail());
        if (!users.isEmpty()) {
            if (users.getFirst().getLozinka().equals(user.getLozinka())) {
//                String session_ID = UUID.randomUUID().toString();
//                Cookie sessionCookie = new Cookie("SESSION_ID", session_ID);
//                sessionCookie.setAttribute("username", user.getEmail());
//                sessionCookie.setHttpOnly(false);
//                sessionCookie.setSecure(true);
//                sessionCookie.setPath("/");
//                sessionCookie.setMaxAge(24 * 60 * 60);
//
//                response.addCookie(sessionCookie);
                HttpSession session = request.getSession();
                session.setAttribute("username", users.getFirst().getImeKorisnika());
                session.setAttribute("sif_korisnika", users.getFirst().getSifKorisnika());
                return ResponseEntity.ok("Logged in");
                //return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
            } else {

                return new ResponseEntity<>("User signed-in failed.", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> logout(HttpServletRequest request) {
//        Cookie sessionCookie = new Cookie("SESSION_ID", null);
//        sessionCookie.setHttpOnly(true);
//        sessionCookie.setSecure(true);
//        sessionCookie.setPath("/");
//        sessionCookie.setMaxAge(0);
//        response.addCookie(sessionCookie);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return ResponseEntity.ok("Success!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in!");
    }
}
