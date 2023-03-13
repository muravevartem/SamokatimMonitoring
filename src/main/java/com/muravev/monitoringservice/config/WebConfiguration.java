package com.muravev.monitoringservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://1218407-cu57808.tw1.ru",
                        "https://1218407-cu57808.tw1.ru"
                )
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
