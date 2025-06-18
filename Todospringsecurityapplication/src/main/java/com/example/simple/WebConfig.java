package com.example.simple;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

	public void addCorsMapping(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*")
		.allowedOrigins("*").allowedHeaders("*").allowCredentials(true).exposedHeaders("Authorization");
	}
}
