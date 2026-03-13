package com.project.Fitness_App.Security;

import com.project.Fitness_App.Entity.UserRole;
import com.project.Fitness_App.Entity.user;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class JwtUtils {

    private String jwtSecreteKey="wnio2ut9irffnmHJB&hnunUN9NHBL7k0due4ncUYojsn7ihcjndoD0ukconx0u";
    private int jwtExparions=172800000;

    public String generateToken(String email, UserRole role){
        return Jwts.builder()
                .subject(email)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() +jwtExparions))
                .signWith(key())
                .compact();
    }
    private SecretKey key(){
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));

    }


    public Claims extractAllclaims(String token) {
        return Jwts.parser()
                .verifyWith( key())
                .build().parseSignedClaims(token)
                .getPayload();
    }
    public String extractUserEmail(String token){
        return extractAllclaims(token).getSubject();
    }

    public String extractroles(String token) {
        return extractAllclaims(token).get("role",String.class);
    }

    public boolean isTokenValid(String token, String email) {
        String useremail=extractUserEmail(token);
        return (useremail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllclaims(token)
                .getExpiration()
                .before(new Date());
    }


}
