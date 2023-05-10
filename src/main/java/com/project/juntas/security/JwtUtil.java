package com.project.juntas.security;

import com.project.juntas.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    public <T> T extractClaim(String token, Function<Claims, T> function) {
        var claims = extractAllClaims(token);
        return function.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey()).build()
                .parseClaimsJws(token).getBody();
    }

    private static SecretKey getKey() {
        var SECRET_KEY = "82E6888B-5928-45F7-8579-D055C7C474ED";
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractSubject(String token) { return extractClaim(token, Claims::getSubject); }

    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration); }


    public Long extractUserId(String token) {
        return Long.valueOf(extractAllClaims(token.substring(7)).get("user_id").toString());
    }

    public static String generateToken(User user) {

        Map<String, Object> claims = Map.of(
                "authority", user.getRole(),
                "user_id", user.getId()
        );

        return createToken(claims, user.getEmail());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 700000*24))
                .addClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractSubject(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
