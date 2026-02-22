package com.example.springbootjwtgraphql.api.rest.dto;

public record UserResponse(String id, String firstName, String lastName, String email, String username) {
}
