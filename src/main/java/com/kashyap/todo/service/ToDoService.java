package com.kashyap.todo.service;

import java.util.List;

import com.kashyap.todo.dto.ToDoDTO;

public interface ToDoService {
	
	ToDoDTO  save(ToDoDTO toDoDTO);
	List<ToDoDTO> getAllTodos();
	List<ToDoDTO> getAllCompletedTodos();
	List<ToDoDTO> getALLIncompleteTodos();
	ToDoDTO update(ToDoDTO toDoDTO);
	void delete(Long id);
	ToDoDTO findToDoByID(Long id);
	ToDoDTO markCompleted(Long id,Boolean completed);
	
	

}
