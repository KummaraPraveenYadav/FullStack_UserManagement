package com.example.simple.security;

import com.example.simple.SpringApplicationContext;

public class SecurityConstants {
	public static final String HEADER_STRING = "Authorization";
	public static final String USER_ID = "userId";
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties)SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
	
	public static String getTokenExpirationTime() {
		AppProperties appProperties = (AppProperties)SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenExpirationTime();
	}
}
