package com.example.simple.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.pojo.TodoDto;
import com.example.simple.pojo.TodoRequest;
import com.example.simple.pojo.TodoResponse;
import com.example.simple.service.TodoService;
import com.example.simple.singleton.ModelMapperSingleTone;

@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;

	private ModelMapper modelMapper = ModelMapperSingleTone.modelMapper();

	@GetMapping("/getAll")
	public ResponseEntity<List<TodoResponse>> getTodosFromController() {
		List<TodoDto> todoDtos = todoService.getAllTodosFromServiceImpl();
		List<TodoResponse> responses = todoDtos.stream().map(todo -> modelMapper.map(todo, TodoResponse.class))
				.toList();
		return ResponseEntity.ok(responses);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/add")
//	@PostAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest todoRequest,
			Authentication authentication) {
		TodoDto todoDto = modelMapper.map(todoRequest, TodoDto.class);
		boolean isAdmin = authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
		TodoDto savedTodoDto = todoService.addTodo(todoDto);
		if (isAdmin) {
			return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedTodoDto, TodoResponse.class));
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
//		return ResponseEntity.ok(modelMapper.map(savedTodoDto, TodoResponse.class));
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
		TodoDto todoDto = modelMapper.map(todoRequest, TodoDto.class);
		TodoDto updatedTodo = todoService.updateTodo(id, todoDto);
		return ResponseEntity.ok(modelMapper.map(updatedTodo, TodoResponse.class));
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/del/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
		boolean deleted = todoService.deleteTodo(id);
		if (deleted) {
			return ResponseEntity.ok("Todo deleted successfully.");
		} else {
			return ResponseEntity.badRequest().body("Todo not found.");
		}
	}

}