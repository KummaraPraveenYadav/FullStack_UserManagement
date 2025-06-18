package com.example.simple.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.simple.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	void deleteUserByUserId(String userId);
}
