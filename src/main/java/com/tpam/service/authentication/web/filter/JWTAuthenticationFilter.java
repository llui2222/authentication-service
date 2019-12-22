package com.tpam.service.authentication.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpam.service.authentication.security.SecurityConstants;
import com.tpam.service.authentication.security.TokenProvider;
import com.tpam.service.authentication.web.dto.UserCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager, final TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/token", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
        throws AuthenticationException {

        try {
            final UserCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
            final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), Collections.emptyList());
            return authenticationManager.authenticate(authToken);
        }
        catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication auth) {

        final String token = tokenProvider.generateToken(auth);
        response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}