package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.UserDTO;
import com.cg.oam.exception.InvalidDataException;

/**
 * The Interface IAdminService.
 */
public interface IAdminService {
	
	/**
	 * Adds the admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public AdminDTO addAdmin(AdminDTO admin) throws InvalidDataException;
	//public AdminDTO addAdmin(UserDTO admin) throws InvalidDataException;
	
	/**
	 * Removes the admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public AdminDTO removeAdmin(AdminDTO admin) throws InvalidDataException;
	
	/**
	 * Removes the admin.
	 *
	 * @param id the id
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public AdminDTO removeAdmin(int id) throws InvalidDataException;
	//public AdminDTO removeAdmin(UserDTO admin) throws InvalidDataException;
	
	/**
	 * Update admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public AdminDTO updateAdmin(AdminDTO admin) throws InvalidDataException;
	//public AdminDTO updateAdmin(UserDTO admin) throws InvalidDataException;
	
	/**
	 * Show all admins.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<AdminDTO> showAllAdmins() throws InvalidDataException;
	
	/**
	 * Validate admin.
	 *
	 * @param id the id
	 * @param password the password
	 * @return true, if successful
	 * @throws InvalidDataException the invalid data exception
	 */
	public boolean validateAdmin(int id, String password) throws InvalidDataException;

}
