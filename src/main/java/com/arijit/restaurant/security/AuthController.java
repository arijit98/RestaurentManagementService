package com.arijit.restaurant.security;

import com.arijit.restaurant.user.User;
import com.arijit.restaurant.user.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${google.client.id}")
    private String googleClientId;

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

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest googleLoginRequest) {
        try {
            String token = googleLoginRequest.getToken();

            // Verify the Google token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(token);
            if (idToken == null) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid Google token"));
            }

            // Extract user information from the token
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // Check if the user exists in the database by email
            Optional<User> userOptional = userService.findByUsername(email);
            User user;

            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                // Create new user with Google info
                user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(passwordEncoder.encode("google-auth-" + System.currentTimeMillis()));
                user.setRoles("staff"); // Default role
                user = userService.createUser(user);
            }

            // Generate JWT
            String jwtToken = jwtUtil.generateToken(user.getEmail(), user.getRoles());
            return ResponseEntity.ok(Map.of("token", jwtToken, "role", user.getRoles()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Google login failed: " + e.getMessage()));
        }
    }
}