package com.sportico.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.sportico.CorsConfig;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;  // Custom user details service
    private final CustomJwtAuthenticationFilter jwtFilter;  // JWT filter
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;  // Custom entry point for 401 errors

    // Configure AuthenticationManager to use custom user details service and password encoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // Password encoder (BCrypt) for encoding passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain for configuring authorization rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Disable CSRF for stateless authentication
            .authorizeRequests(authorizeRequests -> authorizeRequests
            		.requestMatchers("/user/login", "/user/adduser", "/v*/api-doc*/**", "/swagger-ui/**").permitAll() // Allow login
            		.requestMatchers("/user/**").permitAll()
            		 .requestMatchers("/tournament/**", "/coaching-sessions/**").permitAll()            		
            		.requestMatchers("/admin/**").hasRole("ADMIN")  // Admin only access
                .requestMatchers("/coach/**").hasRole("COACH")  // Coach only access
                .anyRequest().permitAll() // All other requests need authentication
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint)  // Handle unauthorized access
            )
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless authentication
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter to the filter chain

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Adjust as needed
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // ✅ 2. Global CORS Filter (Fixes missing header issue)
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
    
    
    // Authentication provider using custom user details service and password encoder
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);  // Set the custom user details service
        provider.setPasswordEncoder(passwordEncoder());  // Set the password encoder
        return provider;
    }
}

