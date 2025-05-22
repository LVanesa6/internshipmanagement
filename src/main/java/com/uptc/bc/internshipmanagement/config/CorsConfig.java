package com.uptc.bc.internshipmanagement.config;

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
                        .allowedOrigins("http://localhost:3000")  // Origen permitido
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                        .allowedHeaders("*")  // Permitir todos los encabezados
                        .allowCredentials(true);  // Si usas cookies o autenticación
            }
        };
    }
}
