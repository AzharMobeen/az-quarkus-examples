package com.az.quarkus.example.service;

import com.az.quarkus.example.config.JwtConfig;
import jakarta.enterprise.context.ApplicationScoped;

import io.jsonwebtoken.Jwts;
import jakarta.inject.Inject;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class JwtService {

    private final JwtConfig jwtConfig;

    @Inject
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String generateToken(String username, List<String> roles) {
        byte[] decodedKey = Base64.getDecoder().decode(jwtConfig.secretKey());
        Key key = new SecretKeySpec(decodedKey, "HmacSHA256"); // Use a secure hashing algorithm
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
}
