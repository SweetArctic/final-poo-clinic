package com.clinica.proyecto.infrastructure.security;

import com.clinica.proyecto.infrastructure.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    private final SecretKey key;
    public AuthTokenService(@Value("${JWT_SECRET:change-me}") String secret) {
        byte[] raw = secret.getBytes(StandardCharsets.UTF_8);
        if (raw.length < 32) {
            try {
                raw = MessageDigest.getInstance("SHA-256").digest(raw);
            } catch (NoSuchAlgorithmException ignored) { }
        }
        this.key = Keys.hmacShaKeyFor(raw);
    }
    public String generate(Usuario u) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(String.valueOf(u.getId()))
                .claim("email", u.getEmail())
                .claim("rol", u.getRol().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(86400)))
                .signWith(key)
                .compact();
    }
    public Optional<Long> verify(String token) {
        try {
            String sub = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
            return Optional.of(Long.parseLong(sub));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
