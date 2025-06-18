package com.example.simple.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.simple.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>{

	RoleEntity findByName(String name);

}
