package com.example.springbootjwtgraphql.application.security;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PublicEndpoints {
    public static final List<String> ENDPOINTS = List.of(
        "/auth/login",
        "/auth/refresh",
        "/graphiql"
    );
}