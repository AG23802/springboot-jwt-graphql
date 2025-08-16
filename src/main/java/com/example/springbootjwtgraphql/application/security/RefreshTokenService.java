package com.example.springbootjwtgraphql.application.security;

import com.example.springbootjwtgraphql.domain.repositories.RefreshTokenRepository;
import com.example.springbootjwtgraphql.application.exceptions.RefreshTokenException;
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
        // Find and delete any existing token for this user to ensure only one is
        // active.
        refreshTokenRepository.findByUsername(username).ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public String validateAndRotateRefreshToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RefreshTokenException("Missing refresh token");
        }

        // Step 1: Find the token in the database. If not found, it's invalid.
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RefreshTokenException("Invalid refresh token"));

        // Step 2: Check if the token has expired.
        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Refresh token expired");
        }

        // Step 3: Delete the old token to prevent reuse.
        refreshTokenRepository.delete(refreshToken);

        // Step 4: Return the username to generate a new access and refresh token.
        return refreshToken.getUsername();
    }

    // Additional method to allow manual revocation (e.g., on logout)
    public void deleteByUsername(String username) {
        refreshTokenRepository.deleteByUsername(username);
    }
}