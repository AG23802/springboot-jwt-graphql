package com.example.springbootjwtgraphql.api.rest.dto;

public record AuthResponse(String accessToken, String refreshToken) {
}
