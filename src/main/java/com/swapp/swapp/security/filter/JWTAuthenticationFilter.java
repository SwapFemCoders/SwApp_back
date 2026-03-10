package com.swapp.swapp.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapp.swapp.entity.User;
import com.swapp.swapp.security.CustomAuthenticationManager;
import com.swapp.swapp.security.UserDetail;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationManager customAuthenticationManager;
    private final String secret;


    public JWTAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager, String secret) {
        this.customAuthenticationManager = customAuthenticationManager;
        this.secret = secret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
            return customAuthenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetail userDetail = (UserDetail) authResult.getPrincipal();
        Integer userId = userDetail.getUser().getId();
        // List<String> roles = authResult.getAuthorities().stream()
        //         .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
        //         .collect(Collectors.toList());

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withClaim("userId", userId)
                // .withClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60000)))
                .sign(Algorithm.HMAC512(this.secret));
        response.addHeader("Authorization", "Bearer " + token);
    }

}

