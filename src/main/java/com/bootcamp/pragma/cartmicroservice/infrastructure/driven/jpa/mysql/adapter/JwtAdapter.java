package com.bootcamp.pragma.cartmicroservice.infrastructure.driven.jpa.mysql.adapter;

import com.bootcamp.pragma.cartmicroservice.domain.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtAdapter {
    @Value("${jwtsecret}")
    private String jwtSecret;

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Role getRoleFromToken(String token) {
        return Role.valueOf(getClaimFromToken(token, claim -> claim.get("role", String.class)));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public boolean tokenIsValid(String token) {
        return !isTokenExpired(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] secretBytes = Decoders.BASE64URL.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
