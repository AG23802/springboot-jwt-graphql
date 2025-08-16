// package com.example.springbootjwtgraphql.application.security;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;

// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// @ControllerAdvice
// public class JwtExceptionHandler {

//     @ExceptionHandler(JwtAuthenticationException.class)
//     public void handleJwtException(JwtAuthenticationException ex, HttpServletResponse response) throws IOException {
//         // Optionally, you can format this as a GraphQL errors array
//         String graphqlError = "{ \"data\": null, \"errors\": [{ \"message\": \"" + ex.getMessage()
//                 + "\", \"extensions\": {\"code\": \"UNAUTHENTICATED\"} }] }";

//         response.setStatus(HttpStatus.UNAUTHORIZED.value());
//         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//         response.getWriter().write(graphqlError);
//     }
// }