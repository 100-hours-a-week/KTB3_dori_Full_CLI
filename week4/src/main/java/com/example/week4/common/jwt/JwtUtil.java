package com.example.week4.common.jwt;

import com.example.week4.domain.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Getter
public class JwtUtil {
    private final Key key;
    private final long expiration;
    private final String prefix;
    private final String header;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-expiration-time}") long expiration,
            @Value("${jwt.prefix}") String prefix,
            @Value("${jwt.header}") String header
    ) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expiration = expiration * 1000;
        this.prefix = prefix;
        this.header = header;
    }

    public String createAccessToken(User user) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("email", user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("email", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("email", String.class);
        }
    }



}