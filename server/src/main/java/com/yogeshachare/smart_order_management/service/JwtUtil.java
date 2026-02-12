package com.yogeshachare.smart_order_management.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Jwts;

public class JwtUtil {
    @Value("${jwt.secret}")
    private Key secret;

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(secret)
                .compact();
    }
}
