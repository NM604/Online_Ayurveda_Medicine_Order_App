package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;

public interface IAdminService {
	
	public AdminDTO addAdmin(AdminDTO admin) throws InvalidDataException;
	//public AdminDTO addAdmin(UserDTO admin) throws InvalidDataException;
	
	public AdminDTO removeAdmin(AdminDTO admin) throws InvalidDataException;
	public AdminDTO removeAdmin(int id) throws InvalidDataException;
	//public AdminDTO removeAdmin(UserDTO admin) throws InvalidDataException;
	
	public AdminDTO updateAdmin(AdminDTO admin) throws InvalidDataException;
	//public AdminDTO updateAdmin(UserDTO admin) throws InvalidDataException;
	
	public List<AdminDTO> showAllAdmins() throws InvalidDataException;
	public boolean validateAdmin(String id, String password) throws InvalidDataException;

}
