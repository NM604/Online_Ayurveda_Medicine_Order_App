package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IUserService;

/**
 * The Class UserAPI.
 */
@RestController
@RequestMapping(value="/oam/userinterface")
@Validated
public class UserAPI {
	
	/** The user service. */
	@Autowired
	IUserService userService;
	
	/** The environment. */
	@Autowired
	Environment environment;
	
	/**
	 * Gets the all Users.
	 *
	 * @return the all Users
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws InvalidDataException {
		List<UserDTO> users = userService.showAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/**
	 * Gets an user with Id.
	 *
	 * @return the user with requested Id
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/user/{Id}")
	public ResponseEntity<UserDTO> getAdmin(@PathVariable 
													@Min(value = 1, message = "User ID should be greater than 0") Integer Id) 
															throws InvalidDataException {
		UserDTO user = userService.showUser(Id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/**
	 * Adds the User.
	 *
	 * @param user - the user
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PostMapping(value = "/user")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO user) throws InvalidDataException{
		userService.addUser(user);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + user.getUserId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	/**
	 * Update User.
	 *
	 * @param userId - the user id
	 * @param user - the user
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PutMapping(value = "/user/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable 
												@Min(value = 1, message = "User ID should be greater than 0") Integer userId, 
												@Valid @RequestBody UserDTO user)
														throws InvalidDataException{
		userService.updateUser(user);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	/**
	 * Removes the User.
	 *
	 * @param userId - the user id
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable
												@Min(value = 1, message = "User ID should be greater than 0") Integer userId) 
														throws InvalidDataException{
		userService.removeUser(userId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	/**
	 * Validate user.
	 *
	 * @param user - the user
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PostMapping(value = "/uservalidate")
	public ResponseEntity<String> validateUser(@Valid @RequestBody UserDTO user) throws InvalidDataException{
		String successMessage = "";
		if (userService.validateUser(user.getUserId(),user.getUserName())) {
			successMessage += "Valid Credentials";
		}
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
