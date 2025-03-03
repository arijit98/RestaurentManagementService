package com.arijit.restaurant.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Hardcoded users (replace with actual user authentication logic)
        if ("user".equals(username) && "password".equals(password)) {
            String token = jwtUtil.generateToken(username, "ROLE_USER");
            return ResponseEntity.ok(token);
        } else if ("admin".equals(username) && "password".equals(password)) {
            String token = jwtUtil.generateToken(username, "ROLE_ADMIN");
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}