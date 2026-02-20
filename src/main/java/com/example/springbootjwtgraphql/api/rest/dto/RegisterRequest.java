package com.example.springbootjwtgraphql.api.rest.dto;

public record RegisterRequest(
        String firstName,
        String lastName,
        String password,
        String confirmPassword,
        String email
) {};