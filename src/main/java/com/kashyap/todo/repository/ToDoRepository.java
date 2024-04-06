package com.kashyap.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kashyap.todo.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	List<ToDo> findByCompletedTrue();
	List<ToDo> findByCompletedFalse();

}
