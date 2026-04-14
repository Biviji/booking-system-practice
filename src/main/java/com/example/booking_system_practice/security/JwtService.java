package com.example.booking_system_practice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Value("${security.jwt.expiration-ms}")
    private long expirationTime;

//    We take String secretKey and transform it into
//    the object Key so that JWT library could understand it

    private Key getSigningKey() {

//        We transoform String secretKey into bytes' array, because
//        JJWT library needs bytes to create a cryptographic key, not
//        ordinary Java String

        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

//        We pass on those bytes into JJWT, since basing on 'em the library
//        will create and object Key which we'll use while creating a token and
//        while reading a token (to sign it and to check the sign). In general,
//        this method returns a ready Key for JWT

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long userId, String email) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))

//                We're calling getSigningKey() to take the secretKey, transoforming it into
//                the bytes array, then we create the obejct Key and return it here to signWith

                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    Use generics to extract any field from the token

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {

//        Since we put "email" into the subject in "GenerateToken", we take
//        it back away via using Claims::getSubject

        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {

//        "When you get the subject "claims", take from it the field
//        'useId' and interpret as Long

        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return extractedEmail.equals(email) && !isTokenExpired(token);
    }

}
