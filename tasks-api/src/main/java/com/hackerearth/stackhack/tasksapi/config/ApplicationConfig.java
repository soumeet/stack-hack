package com.hackerearth.stackhack.tasksapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackerearth.stackhack.tasksapi.mapper.TaskMapper;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
	@Bean
	public TaskMapper phoneMapper() {
		TaskMapper taskMapper = new TaskMapper();
		return taskMapper;
	}
}
