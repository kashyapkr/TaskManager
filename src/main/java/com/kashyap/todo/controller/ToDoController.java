package com.kashyap.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kashyap.todo.dto.ToDoDTO;
import com.kashyap.todo.service.ToDoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ToDoController {

	private ToDoService toDoService;

	@GetMapping("/todos")
	public String home(Model model) {
		List<ToDoDTO> todos = toDoService.getAllTodos();
		model.addAttribute("todos", todos);
		model.addAttribute("newTodo", new ToDoDTO());
		return "index";

	}

	@PostMapping("/save/todos")
	public String saveToDo(@ModelAttribute("newTodo") ToDoDTO toDoDTO) {
		toDoService.save(toDoDTO);
		return "redirect:/todos";
	}


	@GetMapping("/todos/delete/{id}")
	public String deleteTodo(@PathVariable("id") Long id) {
		toDoService.delete(id);
		return "redirect:/todos";
	}

	@PostMapping("/update-completed/{todoId}")
	public String updateCompletedStatus(@PathVariable Long todoId, @RequestParam boolean completed) {
		toDoService.markCompleted(todoId, completed);
		return "redirect:/todos";
	}

	@GetMapping("/update-todo/{id}")
	public String showUpdateTodoForm(@PathVariable("id") Long id, Model model) {
		ToDoDTO existingTodo = toDoService.findToDoByID(id);
		model.addAttribute("editedTodo", existingTodo);
		return "edit";
	}

	@PostMapping("/update-todo/{id}")
	public String updateTodo(@PathVariable Long id, @ModelAttribute("editedTodo") ToDoDTO editedTodo) {
		toDoService.update(editedTodo);
		return "redirect:/todos";
	}
	
	@GetMapping("/list-completed")
	public String listCompletedTodos(Model model) {
		List<ToDoDTO> compleed_list = toDoService.getAllCompletedTodos();
		System.out.println(compleed_list);
		model.addAttribute("completed", compleed_list);
		return "completed";
		
	}
	
	@GetMapping("/incomplete")
	public String listIncomplete(Model model) {
		List<ToDoDTO> incomplete = toDoService.getALLIncompleteTodos();
		 for (ToDoDTO todo : incomplete) {
		        System.out.println("Title: " + todo.getTitle() + ", Description: " + todo.getDescription());
		    }
		model.addAttribute("incomplete", incomplete);
		return "incomplete";
		
	}

}
