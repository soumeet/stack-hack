package com.hackerearth.stackhack.tasksapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerearth.stackhack.tasksapi.dto.AddTaskDTO;
import com.hackerearth.stackhack.tasksapi.dto.TaskDTO;
import com.hackerearth.stackhack.tasksapi.entity.Task;

public class TaskMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public TaskDTO map(Task label) {
		TaskDTO taskDTO = modelMapper.map(label, TaskDTO.class);
		return taskDTO;
	}
	
	public Task map(TaskDTO taskDTO) {
		Task task = modelMapper.map(taskDTO, Task.class);
		return task;
	}
	
	public Task map(AddTaskDTO addTaskDTO) {
//		Task task = modelMapper.map(addTaskDTO, Task.class);
		Task task = new Task();
		task.setDueDate(addTaskDTO.getDueDate());
		task.setLabelCode(addTaskDTO.getLabelCode());
		task.setStatusCode(addTaskDTO.getStatusCode());
		task.setTaskName(addTaskDTO.getTaskName());
		task.setUserId(addTaskDTO.getUserId());
		return task;
	}
}
