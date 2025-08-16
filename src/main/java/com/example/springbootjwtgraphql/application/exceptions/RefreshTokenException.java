package com.example.springbootjwtgraphql.application.exceptions;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String message) {
        super(message);
    }
}