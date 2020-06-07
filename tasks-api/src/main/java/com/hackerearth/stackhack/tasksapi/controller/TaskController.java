 package com.hackerearth.stackhack.tasksapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerearth.stackhack.tasksapi.dto.AddTaskDTO;
import com.hackerearth.stackhack.tasksapi.dto.TaskDTO;
import com.hackerearth.stackhack.tasksapi.dto.UpdateTaskDTO;
import com.hackerearth.stackhack.tasksapi.entity.Task;
import com.hackerearth.stackhack.tasksapi.mapper.TaskMapper;
import com.hackerearth.stackhack.tasksapi.repository.TaskRepository;

@RestController
@RequestMapping(path="/task")
public class TaskController {
	private static final Logger log = LogManager.getLogger(TaskController.class);
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskMapper taskMapper;
	
	@GetMapping(path="/test")
	public String test() {
		log.info("test(): Test Successful");
		String testData = "Test Successful!";
		return testData;
	}
	
	@GetMapping(path="/all")
	public List<TaskDTO> getAllTask(@RequestParam("userId") Integer userId){
		log.info("getAllTask: userId: " + userId);
		List<TaskDTO> taskList = taskRepository.getTasksByUserId(userId)
				.stream()
				.map(task -> taskMapper.map(task))
				.collect(Collectors.toList());
		log.info("getAllTask: task-found: " + taskList.size());
		return taskList;
	}
	
	@GetMapping(path="/id/{taskId}", produces="application/json")
	public TaskDTO getTask(@PathVariable("taskId") Integer taskId){
		log.info("getTask: taskId: " + taskId);
		Task task = taskRepository.getOne(taskId);
		log.info("getTask: task: " + task.toString());
		return taskMapper.map(task);
	}
	
	@PostMapping(path="/add", produces="application/json")
	public TaskDTO addTask(@RequestBody AddTaskDTO addTaskDTO) {
		log.info("addTask: " + addTaskDTO);
		Task task = taskMapper.map(addTaskDTO);
		task = taskRepository.save(task);
		TaskDTO taskDTO = taskMapper.map(task);
		log.info("addTask: added " + taskDTO);
		return taskDTO;
	}
	
	@PostMapping(path="/update", produces="application/json")
	public TaskDTO updateTask(@RequestBody UpdateTaskDTO updateTaskDTO) {
		log.info("updateTask: " + updateTaskDTO);
		Task task = taskRepository.getOne(updateTaskDTO.getTaskId());
		task.setStatusCode(updateTaskDTO.getStatusCode());
		task = taskRepository.save(task);
		TaskDTO taskDTO = taskMapper.map(task);
		log.info("updateTask: updated " + taskDTO);
		return taskDTO;
	}
}
