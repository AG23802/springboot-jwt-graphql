package com.example.springbootjwtgraphql.domain.repositories;

import com.example.springbootjwtgraphql.domain.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsername(String username); // optional: for logout
    Optional<RefreshToken> findByUsername(String username);
}