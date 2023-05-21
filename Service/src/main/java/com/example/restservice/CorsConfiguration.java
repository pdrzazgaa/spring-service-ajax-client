package com.example.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/persons/**") // określ ścieżkę do kontrolera
                        .allowedOrigins("*") // dozwolone źródła (np. "*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // dozwolone metody
                        .allowedHeaders("*"); // dozwolone nagłówki
            }
        };
    }
}

