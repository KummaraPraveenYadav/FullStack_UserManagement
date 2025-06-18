package com.example.simple.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.simple.globalexecptions.UserNotFoundException;
import com.example.simple.pojo.UserDto;

public interface UserService extends UserDetailsService {

	public UserDto cteateUser(UserDto userDto);

	public UserDto getuserDetailsByEmail(String email) throws UserNotFoundException;

	public UserDto getUserByUserId(String userId) throws UserNotFoundException;

	public boolean removeUserByUserId(String userId) ;
}
