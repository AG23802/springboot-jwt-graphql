package com.example.springbootjwtgraphql.api.rest.dto;

public record LoginResponse(AuthResponse auth, UserResponse user) {
}
