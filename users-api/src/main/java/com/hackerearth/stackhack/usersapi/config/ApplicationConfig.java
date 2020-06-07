package com.hackerearth.stackhack.usersapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hackerearth.stackhack.usersapi.mapper.UserMapper;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
	@Bean
	public UserMapper phoneMapper() {
		UserMapper taskMapper = new UserMapper();
		return taskMapper;
	}
}
