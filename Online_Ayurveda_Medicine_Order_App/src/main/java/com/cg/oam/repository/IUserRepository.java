package com.cg.oam.repository;

import java.util.List;

import com.cg.oam.dto.UserDTO;

public interface IUserRepository {
	
	public UserDTO addUser(UserDTO user);
	
	public UserDTO updateUser(UserDTO user);
	
	public UserDTO removeUser(UserDTO user);
	
	public boolean validateUser(int userId, String userName);
	
	public List<UserDTO> showAllUsers();
}
