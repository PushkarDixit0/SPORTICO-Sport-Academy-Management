package com.sportico.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // Generate JWT token upon successful authentication
    public String generateJwtToken(Authentication authentication) {
        log.info("Generating JWT token for: {}", authentication.getName());

        return Jwts.builder()
                .setSubject(authentication.getName()) // Username/email as subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("authorities", getAuthoritiesInString(authentication.getAuthorities()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // Extract username from JWT claims
    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    // Validate JWT and return claims
    public Claims validateJwtToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    // Convert authorities to comma-separated string
    private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    // Extract authorities from claims
    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authString = claims.get("authorities", String.class);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
    }

    // Create authentication token from JWT
    public Authentication populateAuthenticationTokenFromJWT(String jwt) {
        Claims claims = validateJwtToken(jwt);
        String email = getUserNameFromJwtToken(claims);
        List<GrantedAuthority> authorities = getAuthoritiesFromClaims(claims);

        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }
}
