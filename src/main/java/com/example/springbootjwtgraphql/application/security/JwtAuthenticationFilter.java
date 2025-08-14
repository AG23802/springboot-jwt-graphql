package com.example.springbootjwtgraphql.application.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.example.springbootjwtgraphql.application.services.user.UserService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends GenericFilter {

    /**
     * A custom filter that intercepts every HTTP request and checks if the JWT token is valid.
     * Responsibilities:
     *  •	Intercept requests before they hit your controllers.
     * 	•	Look for Authorization: Bearer <token> headers.
     *  •	Validate the token using JwtUtil.
     * 	•	If valid, set the authenticated user in the Spring Security context.
     */

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        chain.doFilter(request, response);
    }
}