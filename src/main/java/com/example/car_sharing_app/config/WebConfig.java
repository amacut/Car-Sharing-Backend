package com.example.car_sharing_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("3600")
    private Long corsMaxAge;

    @Value("http://localhost:4200")
    private String corsAllowedHost;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsAllowedHost)
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .exposedHeaders("Authorization", "Location")
                .allowedHeaders("Origin", "Content-Type", "Accept", "X-Requested-With", "Authorization", "Location")
                .maxAge(corsMaxAge);
    }
}
