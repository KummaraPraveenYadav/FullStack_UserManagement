package com.example.simple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	@Autowired
	private Environment environment;
	
	public String getTokenSecret() {
		return environment.getProperty("myapp.token.secret");
	}
	
	public String getTokenExpirationTime() {
		return environment.getProperty("myapp.token.expiration.time");
	}
}
