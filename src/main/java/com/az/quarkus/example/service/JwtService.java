package com.az.quarkus.example.service;

import com.az.quarkus.example.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import io.jsonwebtoken.Jwts;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;


import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@ApplicationScoped
public class JwtService {

    private final JwtConfig jwtConfig;

    @Inject
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    public String generateToken(String username, List<String> roles) {
        Key key = Keys.hmacShaKeyFor(jwtConfig.secretKey().getBytes());
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        log.info("validate token method started!");
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtConfig.secretKey().getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}