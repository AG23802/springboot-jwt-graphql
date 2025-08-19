package com.example.springbootjwtgraphql.application.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Catches exceptions during security filter processing (before the controller).
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Simple check: if the path starts with /graphql, return GraphQL style
        if (request.getRequestURI().startsWith("/graphql")) {
            String graphqlError = "{ \"data\": null, \"errors\": [{ \"message\": \"" + authException.getMessage() +
                    "\", \"extensions\": {\"code\": \"UNAUTHENTICATED\"} }] }";
            response.getWriter().write(graphqlError);
        } else {
            // Standard REST JSON
            response.getWriter().write("{\"error\": \"" + authException.getMessage() + "\"}");
        }
    }
}