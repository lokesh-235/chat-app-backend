package com.example.ChatApplication.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Value("${frontend.url}")
	String frontendUrl;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
		        .allowedOrigins(frontendUrl)
		        .allowedHeaders("*")
		        .allowedMethods("*")
		        .allowCredentials(true);
	}
	
}
