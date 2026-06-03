package com.btvn.ss17ex5.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = "chuoibimatnayphaitoithieu32kyfutroilen_arcade_game_2026";
    private final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    private final long JWT_EXPIRATION = 3600000L;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().toString());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }


    public String getUsernameFromJWT(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return claimsJws.getPayload().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(authToken);

            String usernameInToken = claimsJws.getPayload().getSubject();

            boolean isUsernameValid = usernameInToken.equalsIgnoreCase(userDetails.getUsername());
            boolean isTokenNotExpired = !claimsJws.getPayload().getExpiration().before(new Date());

            return isUsernameValid && isTokenNotExpired;

        } catch (Exception ex) {
            return false;
        }
    }
}
