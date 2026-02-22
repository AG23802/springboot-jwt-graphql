package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import java.security.Principal;
import java.util.Map;

import com.example.springbootjwtgraphql.api.rest.dto.*;
import com.example.springbootjwtgraphql.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject this

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserResponseMapper userResponseMapper;

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        String accessToken = jwtUtil.generateToken(request.username());
        String refreshToken = refreshTokenService.createRefreshToken(request.username());

        // 3. Fetch User Data (Assuming you have a repository injected)
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userResponse = userResponseMapper.toUserResponse(user);
        var authResponse = new AuthResponse(accessToken, refreshToken);

        LoginResponse loginResponse = new LoginResponse(
                authResponse,
                userResponse
        );

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(Principal principal) {

        // 3. Fetch User Data (Assuming you have a repository injected)
        var user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userResponse = userResponseMapper.toUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        var user = userMapper.toEntity(request);
        // 2. Encode the raw password before saving
        // This turns "password123" into "$2a$10$dyz/SFL..."
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("username", user.getUsername()));
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