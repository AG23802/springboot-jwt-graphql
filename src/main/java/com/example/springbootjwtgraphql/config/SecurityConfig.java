package com.example.springbootjwtgraphql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.springbootjwtgraphql.application.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
//    Spring Security configuration class — defines what’s protected, what’s public, and how security works in your app.
    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    private static final String[] PUBLIC_ENDPOINTS = {
            "/auth/login",      // Example of a public login endpoint
            "/graphiql"         // Example of a public GraphiQL UI
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for stateless authentication
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless session management
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()  // Explicitly permit public endpoints
                        .anyRequest().authenticated()  // Allow any other requests (not matched above) to be public
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource))  // Add the CORS configuration
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // JWT filter
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}