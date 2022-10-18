package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.oam.dto.UserDTO;
import com.cg.oam.entity.User;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IUserRepository;

/**
 * The Class IUserServiceImpl.
 */
@Service(value="userService")
@Transactional
public class IUserServiceImpl implements IUserService{
	
	/** The user repository. */
	@Autowired
	private IUserRepository userRepository;

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public UserDTO addUser(UserDTO user) throws InvalidDataException {
		List<User> optionalUser = userRepository.findByuserName(user.getUserName());
		if(!optionalUser.isEmpty()) {
			throw new InvalidDataException("Service.USER_FOUND");
		}
		User newUser = new User();
		newUser.setUserName(user.getUserName());
		newUser.setUserType(user.getUserType());
		userRepository.save(newUser);
		return user;
	}

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public UserDTO updateUser(UserDTO user) throws InvalidDataException {
		Optional<User> optionalUser = userRepository.findById(user.getUserId());
		User newUser = optionalUser.orElseThrow(() -> new InvalidDataException("Service.USER_NOT_FOUND"));
		newUser.setUserName(user.getUserName());
		return user;
	}

	/**
	 * Removes the user.
	 *
	 * @param userId the user id
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public UserDTO removeUser(int userId) throws InvalidDataException {
		Optional<User> optionalUser = userRepository.findById(userId);
		User newUser = optionalUser.orElseThrow(() -> new InvalidDataException("Service.USER_NOT_FOUND"));
		UserDTO user = new UserDTO(newUser.getUserId(), newUser.getUserName(), newUser.getUserType());
		userRepository.deleteById(userId);
		return user;
	}

	/**
	 * Show all users.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<UserDTO> showAllUsers() throws InvalidDataException {
		Iterable<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(user -> {
			UserDTO newUserDTO = new UserDTO(user.getUserId(), user.getUserName(), user.getUserType());
			userDTOs.add(newUserDTO);
		});
		return userDTOs;
	}

	/**
	 * Validate user.
	 *
	 * @param userId the user id
	 * @param userName the user name
	 * @return true, if successful
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public boolean validateUser(int userId, String userName) throws InvalidDataException{
		Optional<User> optionalUser = userRepository.findById(userId);
		User newUser = optionalUser.orElseThrow(() -> new InvalidDataException("Service.USER_NOT_FOUND"));
		if(!userName.equals(newUser.getUserName())) {
			throw new InvalidDataException("Service.INVALID_CREDENTIALS");
		}
		return true;
	}

	/**
	 * Gets the user.
	 *
	 * @param id the user Id
	 * @return the user DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public UserDTO showAdmin(Integer id) throws InvalidDataException {
		Optional<User> optionalUser = userRepository.findById(id);
		User newUser = optionalUser.orElseThrow(() -> new InvalidDataException("Service.USER_NOT_FOUND"));
		UserDTO user = new UserDTO(newUser.getUserId(), newUser.getUserName(), newUser.getUserType());
		return user;
	}

}
