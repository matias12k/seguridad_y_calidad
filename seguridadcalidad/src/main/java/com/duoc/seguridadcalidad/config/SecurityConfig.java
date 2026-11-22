package com.duoc.seguridadcalidad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
                .contentSecurityPolicy(csp -> csp.policyDirectives(
                    "default-src 'self'; " +
                    "img-src 'self' data: https://images.unsplash.com; " +
                    "style-src 'self' 'unsafe-inline'; " +
                    "script-src 'self' 'unsafe-inline'; " +
                    "frame-ancestors 'self'; " +
                    "form-action 'self'; " +
                    "object-src 'none';"
                ))
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/eventos", "/login", "/detalle", "/api/auth/**", "/api/eventos/public/**", "/css/**", "/h2-console/**").permitAll()
                .requestMatchers("/api/eventos/privado/**").permitAll()
                .anyRequest().permitAll()
            );

        return http.build();
    }
}