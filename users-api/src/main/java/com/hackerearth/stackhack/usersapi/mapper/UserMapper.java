package com.hackerearth.stackhack.usersapi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerearth.stackhack.usersapi.dto.AddUserDTO;
import com.hackerearth.stackhack.usersapi.dto.UserDTO;
import com.hackerearth.stackhack.usersapi.dto.UserResponseDTO;
import com.hackerearth.stackhack.usersapi.entity.User;

public class UserMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public UserDTO map(User label) {
		UserDTO userDTO = modelMapper.map(label, UserDTO.class);
		return userDTO;
	}
	
	public User map(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		return user;
	}
	
	public User map(AddUserDTO addUserDTO) {
		User user = modelMapper.map(addUserDTO, User.class);
		return user;
	}
	
	public UserResponseDTO mapReponse(User label) {
		UserResponseDTO userResponseDTO = modelMapper.map(label, UserResponseDTO.class);
		return userResponseDTO;
	}
}
