package com.arijit.restaurant.auth;

import com.arijit.restaurant.user.User;
import com.arijit.restaurant.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtUtil;

    public AuthController(UserService userService, JwtService jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String googleToken = request.get("token");

        // Validate Google token
        String googleValidateUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + googleToken;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> googleResponse = restTemplate.getForObject(googleValidateUrl, Map.class);
        System.out.println(googleResponse);
        if (googleResponse == null || googleResponse.get("email") == null) {
            return ResponseEntity.badRequest().body("Invalid Google token");
        }

        String email = (String) googleResponse.get("email");
        String name = (String) googleResponse.get("name");
        String pic = (String) googleResponse.get("picture");

        // Find or create user
        User user = userService. findOrCreateUser(email, name, "STAFF");

        // Generate JWT
        String jwtToken = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token", jwtToken, "role", user.getRoles()));
    }
}
