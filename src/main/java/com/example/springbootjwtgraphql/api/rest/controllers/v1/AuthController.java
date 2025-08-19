package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootjwtgraphql.api.rest.dto.LoginRequest;
import com.example.springbootjwtgraphql.api.rest.dto.RefreshTokenRequest;
import com.example.springbootjwtgraphql.application.security.JwtUtil;
import com.example.springbootjwtgraphql.application.security.RefreshTokenService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/v1/auth")
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
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String username = refreshTokenService.validateAndRotateRefreshToken(request.getRefreshToken());

        String newAccessToken = jwtUtil.generateToken(username);
        String newRefreshToken = refreshTokenService.createRefreshToken(username);

        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken));
    }
}