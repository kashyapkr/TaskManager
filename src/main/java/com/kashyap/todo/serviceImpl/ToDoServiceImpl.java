package com.kashyap.todo.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kashyap.todo.dto.ToDoDTO;
import com.kashyap.todo.entity.ToDo;
import com.kashyap.todo.repository.ToDoRepository;
import com.kashyap.todo.service.ToDoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {

	private ToDoRepository toDoRepository;
	private ModelMapper modelMapper;

	@Override
	public ToDoDTO save(ToDoDTO toDoDTO) {
		ToDo todo = modelMapper.map(toDoDTO, ToDo.class);
		ToDoDTO savedDto = modelMapper.map(toDoRepository.save(todo), ToDoDTO.class);
		return savedDto;
	}

	@Override
	public List<ToDoDTO> getAllTodos() {
		List<ToDo> allTodos = toDoRepository.findAll();
		return allTodos.stream().map(todos -> modelMapper.map(todos, ToDoDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ToDoDTO> getAllCompletedTodos() {
		List<ToDo> completedToDos = toDoRepository.findByCompletedTrue();
		return completedToDos.stream().map(completed -> modelMapper.map(completed, ToDoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ToDoDTO> getALLIncompleteTodos() {
		List<ToDo> incompleted = toDoRepository.findByCompletedFalse();
		return incompleted.stream().map(incomplete -> modelMapper.map(incomplete, ToDoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ToDoDTO findToDoByID(Long id) {
		ToDo item = toDoRepository.findById(id).get();
		System.out.println("id beign searched:"+ id);
		System.out.println(item.getTitle());
		ToDoDTO dto = new ToDoDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setCompleted(item.getCompleted());
        System.out.println(dto.getTitle());
        return dto;
		

	}

	@Override
	public ToDoDTO update(ToDoDTO toDoDTO) {
		ToDo toupdate = modelMapper.map(toDoDTO, ToDo.class);
		ToDoDTO updatedDTO = modelMapper.map(toDoRepository.save(toupdate), ToDoDTO.class);
		return updatedDTO;
	}

	@Override
	public void delete(Long id) {
		toDoRepository.deleteById(id);

	}

	@Override
	public ToDoDTO markCompleted(Long id, Boolean completed) {
		ToDo toMarkComplete = toDoRepository.findById(id).get();
		toMarkComplete.setCompleted(completed);
		return modelMapper.map(toDoRepository.save(toMarkComplete), ToDoDTO.class);

	}

}
