
package org.example.whatsappbackend.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {


    @Value("${application.security.jwt.signing-key}")
    private String signingKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateAccessToken(String username) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiration * 60 * 1000)))
                .signWith(createSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(createSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key createSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(signingKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
