package com.tpam.service.authentication.security;

import com.tpam.service.authentication.configuration.properties.JWTProperties;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final JWTProperties jwtProperties;

    public TokenProvider(final JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(final Authentication auth) {

        final Long now = System.currentTimeMillis();
        return Jwts.builder()
            .setSubject(auth.getName())
            // Convert to list of strings.
            // This is important because it affects the way we get them back in the Gateway.
            .claim("authorities", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(now))
            .setExpiration(new Date(now + jwtProperties.getExpiration() * 1000))  // in milliseconds
            .signWith(jwtProperties.getSignatureAlgorithm(), jwtProperties.getSignatureKey().getBytes())
            .compact();
    }
}