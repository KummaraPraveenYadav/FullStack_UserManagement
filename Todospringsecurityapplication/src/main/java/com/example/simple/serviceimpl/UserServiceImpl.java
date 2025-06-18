package com.example.simple.serviceimpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.simple.entity.RoleEntity;
import com.example.simple.entity.UserEntity;
import com.example.simple.globalexecptions.UserNotFoundException;
import com.example.simple.pojo.UserDto;
import com.example.simple.repository.RoleRepository;
import com.example.simple.repository.UserRepository;
import com.example.simple.security.UserPrincipal;
import com.example.simple.service.UserService;
import com.example.simple.singleton.ModelMapperSingleTone;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	private ModelMapper mapper = ModelMapperSingleTone.modelMapper();

	@Override
	public UserDto cteateUser(UserDto userDto) {

		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		UserEntity entity = mapper.map(userDto, UserEntity.class);

		Collection<RoleEntity> rolesEntity = new HashSet<>();
		for (String role : userDto.getRoles()) {
			RoleEntity roleEntity = roleRepository.findByName(role);
			if (roleEntity != null) {
				rolesEntity.add(roleEntity);
			}
		}
		entity.setRoles(rolesEntity);
		
		userRepository.save(entity);

		UserDto returnValue = mapper.map(entity, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if (entity == null)
			throw new UsernameNotFoundException("Email ID not found");

//		return new User(entity.getEmail(), entity.getEncryptedPassword()
//				,true, true, true, true, new ArrayList<>());

		return new UserPrincipal(entity);
	}

	@Override
	public UserDto getuserDetailsByEmail(String email) throws UserNotFoundException {
		UserEntity entity = userRepository.findByEmail(email);
		if (entity == null)
			throw new UserNotFoundException("Email ID not found");
		return mapper.map(entity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) throws UserNotFoundException  {
		UserEntity entity = userRepository.findByUserId(userId);
		if (entity == null) {
			throw new UserNotFoundException("Email ID not found");
		}
		return mapper.map(entity, UserDto.class);
	}
    
	@Transactional
	@Override
	public boolean removeUserByUserId(String userId) {
		userRepository.deleteUserByUserId(userId);
		return true;
	}

}
