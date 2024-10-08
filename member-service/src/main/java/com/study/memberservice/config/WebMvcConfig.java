//package com.study.memberservice.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .exposedHeaders("Access", "Refresh")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("OPTIONS", "PATCH", "GET", "POST", "PUT", "DELETE")
//                .maxAge(3000);
//    }
//}