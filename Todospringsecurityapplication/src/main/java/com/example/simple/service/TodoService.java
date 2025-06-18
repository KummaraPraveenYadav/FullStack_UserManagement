package com.example.simple.service;

import java.util.List;

import com.example.simple.pojo.TodoDto;


public interface TodoService {

	public List<TodoDto> getAllTodosFromServiceImpl();
	public TodoDto getTodoById(Long id);
	public TodoDto addTodo(TodoDto todoDto);
	public boolean deleteTodo(Long id);
	public TodoDto updateTodo(Long id,TodoDto todoDto);
}
