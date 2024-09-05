package com.study.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .exposedHeaders("Access", "Refresh")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("OPTIONS", "PATCH", "GET", "POST", "PUT", "DELETE")
                .maxAge(3000);
    }
}