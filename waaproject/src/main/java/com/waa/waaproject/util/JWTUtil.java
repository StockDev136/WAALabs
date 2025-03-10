package com.waa.waaproject.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtil {
    //d30a0a76-9502-4303-ae37-8c967e8ce458
    public static final String secret = "secret";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long expiration = 5 * 60 * 60 * 60;
    public static final long refreshExpiration = 10 * 60 * 60 * 60 * 60;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JWTUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Overridden to accommodate the refresh token
    public String doGenerateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateRefreshToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh"); // Add a "type" claim
        return doGenerateToken(claims, subject);
    }

    public boolean isRefreshToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return "refresh".equals(claims.get("type"));
        } catch(Exception e){
            return false;
        }
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "access"); // Add a "type" claim
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public boolean isAccessToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return "access".equals(claims.get("type"));
        } catch(Exception e){
            return false;
        }
    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        if (!isAccessToken(token)) {
            return null; // or throw an exception indicating invalid token type
        }
        Claims claims = getAllClaimsFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }


    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    public String getUsernameFromToken(String token) {
        String result = null;
        try {
            result = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
