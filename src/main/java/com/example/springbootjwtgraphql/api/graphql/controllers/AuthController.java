package com.example.springbootjwtgraphql.api.graphql.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootjwtgraphql.api.graphql.dto.LoginRequest;
import com.example.springbootjwtgraphql.application.security.JwtUtil;
import com.example.springbootjwtgraphql.application.security.RefreshTokenService;

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
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        // The service now handles all validation and throws an exception on failure
        String username = refreshTokenService.validateAndRotateRefreshToken(refreshToken);

        String newAccessToken = jwtUtil.generateToken(username);
        String newRefreshToken = refreshTokenService.createRefreshToken(username);

        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken));
    }

    @PostMapping("/test")
    public ResponseEntity<Map<String, String>> test(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(Map.of("test", "testå"));
    }

    @GetMapping("/test500")
    public ResponseEntity<String> test500() {
        // This will cause a NullPointerException, which is an unhandled exception.
        // Spring Boot will catch it and return a 500 Internal Server Error by default.
        throw new NullPointerException("This is a test 500 error.");
    }
}