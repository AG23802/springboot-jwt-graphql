package com.example.springbootjwtgraphql.api.rest.dto;

public record LoginRequest(
    String username,
    String password
) {};