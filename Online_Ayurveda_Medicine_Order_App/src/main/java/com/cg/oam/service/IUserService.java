package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;

/**
 * The Interface IUserService.
 */
public interface IUserService {
	
	/**
	 * Gets the user.
	 *
	 * @param id the user Id
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public UserDTO showUser(Integer id ) throws InvalidDataException;
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public UserDTO addUser(UserDTO user) throws InvalidDataException;
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public UserDTO updateUser(UserDTO user) throws InvalidDataException;
	
	/**
	 * Removes the user.
	 *
	 * @param userId the user id
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public UserDTO removeUser(int userId) throws InvalidDataException;
	
	/**
	 * Show all users.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<UserDTO> showAllUsers() throws InvalidDataException;
	
	/**
	 * Validate user.
	 *
	 * @param userId the user id
	 * @param userName the user name
	 * @return true, if successful
	 * @throws InvalidDataException the invalid data exception
	 */
	public boolean validateUser(int userId, String userName) throws InvalidDataException;

}
