package com.example.simple;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.simple.entity.AuthorityEntity;
import com.example.simple.entity.RoleEntity;
import com.example.simple.entity.UserEntity;
import com.example.simple.repository.AuthorityRepository;
import com.example.simple.repository.RoleRepository;
import com.example.simple.repository.UserRepository;
import com.example.simple.shared.Roles;
import com.example.simple.shared.Utills;

import jakarta.transaction.Transactional;

@Component
public class InitialSetup {

	@Autowired(required=true)
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utills utills;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("From application ready event....");
		
		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
		
		createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
		RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), 
				Arrays.asList(readAuthority, writeAuthority, deleteAuthority));
		
		if(roleAdmin == null) return;
		
		UserEntity adminUser = new UserEntity();
		adminUser.setFirstName("Praveen");
		adminUser.setLastName("Kummara");
		adminUser.setEmail("admin@test.com");
		adminUser.setUserId(utills.generateUserId(30));
		adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("12345678"));
		adminUser.setRoles(Arrays.asList(roleAdmin));
		
		UserEntity alreadyStoredAdminUser  = userRepository.findByEmail("admin@test.com");
		if(alreadyStoredAdminUser == null) {
			userRepository.save(adminUser);
		}
	}
	
	@Transactional
	private AuthorityEntity createAuthority(String name) {
		AuthorityEntity entity =  authorityRepository.findByName(name);
		if(entity == null) {
			entity = new AuthorityEntity(name);
			authorityRepository.save(entity);
		}
		return entity;
	}
	
	@Transactional
	private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {
		RoleEntity entity =  roleRepository.findByName(name);
		if(entity == null) {
			entity = new RoleEntity(name);
			entity.setAuthorities(authorities);
			roleRepository.save(entity);
		}
		return entity;
	}
}
