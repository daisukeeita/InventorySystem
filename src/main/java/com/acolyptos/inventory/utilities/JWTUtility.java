package com.acolyptos.inventory.utilities;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

public class JWTUtility {
  private static final Dotenv DOTENV = Dotenv.load();
  private static final String SECRET_KEY = DOTENV.get("SECRET_KEY");

  public static String generateToken(String username) {
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    long expirationTime = 1000 * 60 * 60; // 1 hour

    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(key, Jwts.SIG.HS256)
        .compact();
  }

  public static boolean validateToken(String token) {
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    try {
      Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token);

      return true;
    } catch (JwtException error) {
      return false;
    }
  }

  public static String extractUsername(String token) {
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();

  }
}
