package com.example.simple.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.simple.entity.AuthorityEntity;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long>{
	AuthorityEntity findByName(String name);
}
