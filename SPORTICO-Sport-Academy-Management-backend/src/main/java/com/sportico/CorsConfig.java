package com.sportico;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // Specify the frontend URL (replace if necessary)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Specify allowed HTTP methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials (cookies, authorization headers, etc.)
            }
        };
    }
}
