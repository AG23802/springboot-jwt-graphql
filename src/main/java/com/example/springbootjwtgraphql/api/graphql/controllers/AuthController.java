package com.example.springbootjwtgraphql.api.graphql.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootjwtgraphql.api.graphql.dto.LoginRequest;
import com.example.springbootjwtgraphql.application.security.JwtUtil;
import com.example.springbootjwtgraphql.application.services.RefreshTokenService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String accessToken = jwtUtil.generateToken(request.getUsername());
        String refreshToken = refreshTokenService.createRefreshToken(request.getUsername());

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken));

    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String username = refreshTokenService.validateRefreshToken(refreshToken); // throws if invalid
        String newAccessToken = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

        @PostMapping("/test")
    public ResponseEntity<Map<String, String>> test(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(Map.of("test", "testå"));
    }
}