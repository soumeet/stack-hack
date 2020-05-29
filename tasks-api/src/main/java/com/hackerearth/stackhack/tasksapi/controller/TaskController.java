 package com.hackerearth.stackhack.tasksapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerearth.stackhack.tasksapi.dto.AddTaskDTO;
import com.hackerearth.stackhack.tasksapi.dto.TaskDTO;
import com.hackerearth.stackhack.tasksapi.entity.Task;
import com.hackerearth.stackhack.tasksapi.mapper.TaskMapper;
import com.hackerearth.stackhack.tasksapi.repository.TaskRepository;

@RestController
@RequestMapping(path="/task")
public class TaskController {
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskMapper taskMapper;
	
	@GetMapping(path="/test")
	public String test() {
		String testData = "Test Successful!";
		return testData;
	}
	
	@GetMapping(path="/all")
	public List<TaskDTO> getAllNos(){
		List<TaskDTO> taskList = taskRepository.findAll()
				.stream()
				.map(task -> taskMapper.map(task))
				.collect(Collectors.toList());
		return taskList;
	}
	
	@GetMapping(path="/{taskId}", produces="application/json")
	public TaskDTO getContact(@PathVariable("taskId") Integer taskId){
		Task task = taskRepository.getOne(taskId);
		return taskMapper.map(task);
	}
	
	@PostMapping(path="/add", produces="application/json")
	public TaskDTO addContact(@RequestBody AddTaskDTO addTaskDTO) {
		Task task = taskMapper.map(addTaskDTO);
		task = taskRepository.save(task);
		TaskDTO taskDTO = taskMapper.map(task);
		return taskDTO;
	}
	
}
