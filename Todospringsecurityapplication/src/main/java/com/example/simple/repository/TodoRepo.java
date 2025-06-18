package com.example.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simple.pojo.TodoEntity;

@Repository
public interface TodoRepo extends JpaRepository<TodoEntity, Long> {
}
