package com.example.springbootjwtgraphql.application.security;

import com.example.springbootjwtgraphql.application.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        // Bypass refresh token endpoint
        if (path.startsWith("/auth/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Only protect GraphQL
        if (!path.startsWith("/graphql")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                sendUnauthorized(response, "Missing or invalid Authorization header");
                return;
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.validateToken(token)) {
                sendUnauthorized(response, "Token expired or invalid");
                return;
            }

            String username = jwtUtil.extractUsername(token);
            if (username == null) {
                sendUnauthorized(response, "Token invalid");
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            logger.warning("JWT processing error: " + ex.getMessage());
            sendUnauthorized(response, "Token processing error");
        }
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        logger.warning("JWT auth error: " + message);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // GraphQL-style JSON error
        String graphqlError = "{ \"data\": null, \"errors\": [ { \"message\": \"" + message + "\", " +
                "\"extensions\": {\"code\": \"UNAUTHENTICATED\"} } ] }";

        response.getWriter().write(graphqlError);
    }
}