package com.example.simple.controller;

import java.util.Arrays;
import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.globalexecptions.UserNotFoundException;
import com.example.simple.pojo.CreateUserRequestModel;
import com.example.simple.pojo.CreateUserResponseModel;
import com.example.simple.pojo.UserDto;
import com.example.simple.pojo.UserResponseModel;
import com.example.simple.service.UserService;
import com.example.simple.shared.Roles;
import com.example.simple.singleton.ModelMapperSingleTone;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins={"http://localhost:4200", "http://localhost:4300"})
public class UserController {

	@Autowired
	UserService userService;

	private ModelMapper modelMapper = ModelMapperSingleTone.modelMapper();

	// registration process
	// @PostAuthorize("hasRole('ADMIN') or returnObject.userId == principal.userId")
	@PostMapping(value = "/createUser", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<CreateUserResponseModel> createUser(
			@Validated @RequestBody CreateUserRequestModel createUserRequestModel) {

		UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
		userDto.setRoles(new HashSet<>(Arrays.asList(Roles.ROLE_USER.name())));

		UserDto createdUser = userService.cteateUser(userDto);

		CreateUserResponseModel response = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}

	// secured endpoints
	@GetMapping(value = "getAll/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) throws UserNotFoundException {
		
		UserDto userDetails = userService.getUserByUserId(userId);
		UserResponseModel response = modelMapper.map(userDetails, UserResponseModel.class);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);

	}

	@PreAuthorize("hasRole('ADMIN') or #userId == principal.userId")
	// @Secured("ROLE_ADMIN")
	@DeleteMapping(path = "/{userId}") // Admin only
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		boolean removeUserByUserId = userService.removeUserByUserId(userId);
		if (removeUserByUserId) {
			return ResponseEntity.ok(" user deleted..");
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied...");
		}
	}
}
