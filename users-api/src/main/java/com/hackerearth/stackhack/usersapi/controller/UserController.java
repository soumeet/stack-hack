 package com.hackerearth.stackhack.usersapi.controller;

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

import com.hackerearth.stackhack.usersapi.dto.AddUserDTO;
import com.hackerearth.stackhack.usersapi.dto.UserDTO;
import com.hackerearth.stackhack.usersapi.dto.UserResponseDTO;
import com.hackerearth.stackhack.usersapi.entity.User;
//import com.hackerearth.stackhack.usersapi.dto.UserDTO;
//import com.hackerearth.stackhack.usersapi.dto.UpdateUserDTO;
//import com.hackerearth.stackhack.usersapi.entity.User;
import com.hackerearth.stackhack.usersapi.mapper.UserMapper;
import com.hackerearth.stackhack.usersapi.repository.UserRepository;

@RestController
@RequestMapping(path="/user")
public class UserController {
	private static final Logger log = LogManager.getLogger(UserController.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMapper userMapper;
	
	@GetMapping(path="/test")
	public String test() {
		log.info("test(): Test Successful");
		String testData = "Test Successful!";
		return testData;
	}
	
	@GetMapping(path="/all")
	public List<String> getAllUser(){
		List<String>userList = userRepository.findAll()
				.stream()
				.map(user -> user.getUserName())
				.collect(Collectors.toList());
		log.info("getAllUser: user-found: " + userList.size());
		return userList;
	}
	
	@PostMapping(path="/authenticate", produces="application/json")
	public Integer authenticateUser(@RequestBody AddUserDTO userDTORequest){
		log.info("authenticateUser: user: " + userDTORequest);
		Integer response = 0;
		User user = userRepository.getByUserName(userDTORequest.getUserName());
		if(user != null)
			if(user.getPassword().equals(userDTORequest.getPassword())) {
				response = 2;
				log.info("authenticateUser: respose: " + response);
				return response;
			}
			else
				response = 1;
		log.info("authenticateUser: respose: " + response);
		return response;
	}
	
	@GetMapping(path="/id/{userId}", produces="application/json")
	public UserResponseDTO getUser(@PathVariable("userId") Integer userId){
		log.info("getUser: userId: " + userId);
		User user = userRepository.getOne(userId);
		log.info("getUser: user: " + user.toString());
		return userMapper.mapReponse(user);
	}
	
	@PostMapping(path="/get", produces="application/json")
	public UserResponseDTO getUser(@RequestBody AddUserDTO userDTORequest){
		log.info("getUser: user: " + userDTORequest);
		UserResponseDTO userResponseDTO = null;
		User user = userRepository.getByUserName(userDTORequest.getUserName());
		if(user != null)
			if(user.getPassword().equals(userDTORequest.getPassword())) {
				userResponseDTO = userMapper.mapReponse(user);
				log.info("getUser: user-found: " + userResponseDTO.toString());
				return userResponseDTO;
			}
		log.info("getUser: user-found: " + userResponseDTO);
		return userResponseDTO;
	}
	
	@PostMapping(path="/add", produces="application/json")
	public UserResponseDTO addUser(@RequestBody AddUserDTO addUserDTO) {
		log.info("addUser: " + addUserDTO);
		User user = userMapper.map(addUserDTO);
		user = userRepository.save(user);
		UserResponseDTO userResponseDTO = userMapper.mapReponse(user);
		log.info("addUser: added " + userResponseDTO);
		return userResponseDTO;
	}
}
