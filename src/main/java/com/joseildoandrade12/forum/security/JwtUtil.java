package com.joseildoandrade12.forum.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(String email) {
        Date tempoExpiracao = new Date(System.currentTimeMillis() + 86400000);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder().subject(email).expiration(tempoExpiracao).signWith(key).compact();
    }

    public String extrairEmail(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
