package com.arijit.restaurant.secutiy;

import com.arijit.restaurant.security.JWTUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JWTUtilTest {

    private final JWTUtil jwtUtil = new JWTUtil();

    @Test
    public void testGenerateTokenForRoleUser() {
        // Generate token for ROLE_USER
        String token = jwtUtil.generateToken("user", "ROLE_ADMIN");

        // Validate token claims
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) jwtUtil) // Using the same key from JWTUtil
                .build()
                .parseSignedClaims(token)
                .getPayload();

        assertEquals("user", claims.getSubject()); // Username
        assertEquals("USER", claims.get("role")); // Role
        assertNotNull(claims.getIssuedAt()); // Token issued date
        assertNotNull(claims.getExpiration()); // Token expiry date
    }

//    @Test
//    public void testGenerateTokenForRoleAdmin() {
//        // Generate token for ROLE_ADMIN
//        String token = jwtUtil.generateToken("adminUser", "ROLE_ADMIN");
//
//        // Validate token claims
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(jwtUtil.key) // Using the same key from JWTUtil
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        assertEquals("adminUser", claims.getSubject()); // Username
//        assertEquals("ROLE_ADMIN", claims.get("role")); // Role
//        assertNotNull(claims.getIssuedAt()); // Token issued date
//        assertNotNull(claims.getExpiration()); // Token expiry date
//    }
}