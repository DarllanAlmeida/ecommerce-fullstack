package com.ecommerce.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta (más adelante la moveremos a application.properties)
    private static final String SECRET =
            "mi-clave-super-secreta-para-jwt-2025-123456789";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );

    // Generar token
    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )
                .signWith(key)
                .compact();

    }

    // Obtener email del token
    public String extractUsername(String token) {

        return extractClaims(token)
                .getSubject();

    }

    // Validar token
    public boolean isTokenValid(String token, String email) {

        return extractUsername(token)
                .equals(email)
                && !isTokenExpired(token);

    }

    // ¿Está expirado?
    private boolean isTokenExpired(String token) {

        return extractClaims(token)
                .getExpiration()
                .before(new Date());

    }

    // Leer claims
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}
