package com.ssafy.match.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//            .allowedOrigins("http://jxy.me") // http://jxy.me/websocket-debug-tool/
//            .allowCredentials(false);
                .allowedMethods("*")
                .allowedOrigins("http://localhost:8081", "http://jxy.me")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}