package com.example.springbootjwtgraphql.application.services;
import com.example.springbootjwtgraphql.domain.repositories.RefreshTokenRepository;
import com.example.springbootjwtgraphql.domain.entities.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(String username) {
        // Optional: delete old token
        refreshTokenRepository.deleteByUsername(username);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public String validateRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken.getUsername();
    }
}