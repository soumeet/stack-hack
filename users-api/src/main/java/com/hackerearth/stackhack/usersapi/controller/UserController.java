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
	UserRepository UserRepository;
	@Autowired
	UserMapper UserMapper;
	
	@GetMapping(path="/test")
	public String test() {
		log.info("test(): Test Successful");
		String testData = "Test Successful!";
		return testData;
	}
	
	@GetMapping(path="/all")
	public List<UserDTO> getAllUser(){
		List<UserDTO> UserList = UserRepository.findAll()
				.stream()
				.map(User -> UserMapper.map(User))
				.collect(Collectors.toList());
		log.info("getAllUser: User-found: " + UserList.size());
		return UserList;
	}
	
	@GetMapping(path="/id/{UserId}", produces="application/json")
	public UserDTO getUser(@PathVariable("UserId") Integer UserId){
		log.info("getUser: UserId: " + UserId);
		User User = UserRepository.getOne(UserId);
		log.info("getUser: User: " + User.toString());
		return UserMapper.map(User);
	}
	
	@PostMapping(path="/get", produces="application/json")
	public UserDTO getUser(@RequestParam("username") String username){
		log.info("getUser: userName: " + username);
		User User = UserRepository.getByUserName(username);
		log.info("getUser: User: " + User.toString());
		return UserMapper.map(User);
	}
	
	@PostMapping(path="/add", produces="application/json")
	public UserDTO addUser(@RequestBody AddUserDTO addUserDTO) {
		log.info("addUser: " + addUserDTO);
		User User = UserMapper.map(addUserDTO);
		User = UserRepository.save(User);
		UserDTO UserDTO = UserMapper.map(User);
		log.info("addUser: added " + UserDTO);
		return UserDTO;
	}
}
