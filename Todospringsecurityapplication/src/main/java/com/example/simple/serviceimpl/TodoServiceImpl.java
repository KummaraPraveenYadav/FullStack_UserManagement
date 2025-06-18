package com.example.simple.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simple.annotation.ExecutionTime;
import com.example.simple.pojo.TodoDto;
import com.example.simple.pojo.TodoEntity;
import com.example.simple.repository.TodoInmemoryRepository;
import com.example.simple.repository.TodoRepo;
import com.example.simple.service.TodoService;
import com.example.simple.singleton.ModelMapperSingleTone;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoInmemoryRepository todoInMemoryRepository;

	@Autowired
	private TodoRepo todoRepo;

	private ModelMapper modelMapper = ModelMapperSingleTone.modelMapper();

	@ExecutionTime
	@Override
	public List<TodoDto> getAllTodosFromServiceImpl() {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		List<TodoEntity> allTodos = todoRepo.findAll();
		List<TodoDto> todoDto = new ArrayList<>();
		allTodos.forEach(todo -> {
			todoDto.add(modelMapper.map(todo, TodoDto.class));
		});
		return todoDto;
	}

	@Override
	public TodoDto getTodoById(Long id) {
		Optional<TodoEntity> todoOpt = todoRepo.findById(id);
		return todoOpt.map(todo -> modelMapper.map(todo, TodoDto.class)).orElse(null);
	}

	@Override
	public TodoDto addTodo(TodoDto todoDto) {

		TodoEntity todo = modelMapper.map(todoDto, TodoEntity.class);
		TodoEntity save = todoRepo.save(todo);
		return modelMapper.map(save, TodoDto.class);
	}
    
	@Transactional
	@Override
	public boolean deleteTodo(Long id) {
		todoRepo.deleteById(id);
		return true;
	}

	@Override
	public TodoDto updateTodo(Long id, TodoDto todoDto) {
		Optional<TodoEntity> todoById = todoRepo.findById(id);
		if (todoById.isPresent()) {
			TodoEntity todoExist = todoById.get();

			todoExist.setName(todoDto.getName());
			todoExist.setDescription(todoDto.getDescription());
			todoExist.setStartTime(todoDto.getStartTime());
			todoExist.setEndTime(todoDto.getEndTime());
			TodoEntity saveTodo = todoRepo.save(todoExist);
			return modelMapper.map(saveTodo, TodoDto.class);
		}
		return null;
	}
}
