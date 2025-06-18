package com.example.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.simple.security.AppProperties;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@ComponentScan(basePackages = {"com.example", "com.example.repository"})
public class Todospringsecurityapplication {

	public static void main(String[] args) {
		SpringApplication.run(Todospringsecurityapplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
}
