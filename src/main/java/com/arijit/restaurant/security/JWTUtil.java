package com.arijit.restaurant.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private final String jwtSecret = "c8811b38a15de63ff33858b24464a4c69d427f939f10e3dfbfd70587146f12bcdac37aa7fb4811a47a1aaeef6d69375a320959e0da6d08e5134692f04849d1561a830ca61610c9333339090f13d707ba81ec88b4c84f23563c6f6aebbe4ff8861640ae3b75d283c08a702087d3044613113f40d73b3b6791d347d8786171b59cbb9bf589581ecfb70a5bca4a749291afdd8d7532a82c8f2a8218d0b872a117722a64f452136a7849fc299283188a01b629ea0ab4df359e9179e281119d6f2bc26339abbf86783fe10767da5c038b421dc6aa6d23df381bdf7eac19e84be4d0f790c292b55df6e6cfa84673fb5e9ddfa01f614fd40a48a8b4836ea37735183b4b";
    private final Key key = Keys.hmacShaKeyFor(this.jwtSecret.getBytes(StandardCharsets.UTF_8));

    // Generate JWT
    public String generateToken(String username, String role) {
        // 1 hour
        long expirationTime = 3600000;
        return Jwts.builder()
                .subject(username)
                .claim("role", role) // Add role in token
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // Validate JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Extract username from JWT
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Extract role from JWT
    public String extractRole(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}