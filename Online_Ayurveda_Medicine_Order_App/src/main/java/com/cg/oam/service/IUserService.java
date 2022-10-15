package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;

public interface IUserService {
	
	public UserDTO addUser(UserDTO user) throws InvalidDataException;
	public UserDTO updateUser(UserDTO user) throws InvalidDataException;
	public UserDTO removeUser(int userId) throws InvalidDataException;
	public List<UserDTO> showAllUsers() throws InvalidDataException;
	public boolean validateUser(int userId, String userName) throws InvalidDataException;

}
