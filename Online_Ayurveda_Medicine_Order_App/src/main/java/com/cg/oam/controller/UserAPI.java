package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IUserService;

@RestController
@RequestMapping(value="/oam/userinterface")
public class UserAPI {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getAllAdmins() throws InvalidDataException {
		List<UserDTO> users = userService.showAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<String> addAdmin(@Valid @RequestBody UserDTO user) throws InvalidDataException{
		userService.addUser(user);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + user.getUserId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/user/{userId}")
	public ResponseEntity<String> updateAdmin(@PathVariable Integer userId, @Valid @RequestBody UserDTO user)
			throws InvalidDataException{
		userService.updateUser(user);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<String> removeAdmin(@PathVariable Integer userId) throws InvalidDataException{
		userService.removeUser(userId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PostMapping(value = "/uservalidate")
	public ResponseEntity<String> validateAdmin(@Valid @RequestBody UserDTO user) throws InvalidDataException{
		String successMessage = "";
		if (userService.validateUser(user.getUserId(),user.getUserName())) {
			successMessage += "Valid Credentials";
		}
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
