package com.example.springbootjwtgraphql.api.graphql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import com.example.springbootjwtgraphql.api.graphql.dto.TokenResponse;
import com.example.springbootjwtgraphql.application.security.JwtUtil;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    JwtUtil jwtUtil;
    
    // Query to get a city by ID
    @MutationMapping
    // ony needed if method name doesnt match to schema
    public TokenResponse login(@Argument("username") String username, @Argument("password") String password) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        String token = jwtUtil.generateToken(username);
        return new TokenResponse(token);
    }
}