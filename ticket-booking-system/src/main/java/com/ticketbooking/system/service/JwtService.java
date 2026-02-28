package com.ticketbooking.system.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Ideally, these should be in application.properties, but we will keep them here for now per your code
    private static final String SECRET_KEY = "303030303101010101010101010101010101010101010101010101010101010101010101010";
    private static final long JWT_EXPIRATION = 86400000; // 1 day
    private static final long REFRESH_EXPIRATION = 604800000; // 7 days

    // 1. Fix: Implement the extractUsername method
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Helper method to extract a specific claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Helper method to generate a token (useful for login)
    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String, Object> extraClaims, String username) {
        return buildToken(extraClaims, username, JWT_EXPIRATION);
    }

    private String buildToken(Map<String, Object> extraClaims, String username, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Helper method to validate if the token belongs to the user and is not expired
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Internal helper to parse all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Decodes your secret key into a cryptographic Key object
    private Key getSignInKey() {
        // Assuming your key is Base64 encoded. If it's just a raw string, use keys.hmacShaKeyFor(SECRET_KEY.getBytes())
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}