package com.arijit.restaurant.security;

import com.arijit.restaurant.user.User;
import com.arijit.restaurant.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getName(), user.getRoles());
                return ResponseEntity.ok(token);
            }
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        User user = userService.createUser(username, password, role);
        return ResponseEntity.ok("User registered with ID: " + user.getId());
    }
}