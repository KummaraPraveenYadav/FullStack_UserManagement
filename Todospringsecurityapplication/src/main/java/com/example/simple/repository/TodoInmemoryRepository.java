package com.example.simple.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.simple.pojo.TodoEntity;

@Repository
public class TodoInmemoryRepository {
 //inmemory logic
  private static final Map<Integer, TodoEntity> map = new HashMap<>();
 
  
  
  public List<TodoEntity> getAllTodosFromRepository(){
	  //return new ArrayList<>(map.values());
	  List<TodoEntity> result = map.values().stream().collect(Collectors.toList());
	  return result;
  }
  
  
}
