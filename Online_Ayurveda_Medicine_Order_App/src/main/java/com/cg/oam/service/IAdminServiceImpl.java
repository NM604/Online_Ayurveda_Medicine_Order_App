package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.entity.Admin;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IAdminRepository;

/**
 * The Class IAdminServiceImpl.
 */
@Service(value="adminService")
@Transactional
public class IAdminServiceImpl implements IAdminService{
	
	/** The admin repository. */
	@Autowired
	private IAdminRepository adminRepository;

	/**
	 * Show all admins.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<AdminDTO> showAllAdmins() throws InvalidDataException {
		Iterable<Admin> admins = adminRepository.findAll();
		List<AdminDTO> adminDTOs = new ArrayList<>();
		admins.forEach(admin -> {
			AdminDTO newAdminDTO = new AdminDTO(admin.getId(), admin.getPassword());
			adminDTOs.add(newAdminDTO);
		});
		return adminDTOs;
	}

	/**
	 * Validate admin.
	 *
	 * @param id the id
	 * @param password the password
	 * @return true, if successful
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public boolean validateAdmin(int id, String password) throws InvalidDataException {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		Admin admin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		if(!password.equals(admin.getPassword())) {
			throw new InvalidDataException("Service.INVALID_CREDENTIALS");
		}
		return true;
	}

	/**
	 * Adds the admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public AdminDTO addAdmin(AdminDTO admin) throws InvalidDataException {
		List<Admin> optionalAdmin = adminRepository.findByPassword(admin.getPassword());
		if(!optionalAdmin.isEmpty()) {
			throw new InvalidDataException("Service.ADMIN_FOUND");
		}
		Admin newAdmin = new Admin();
		newAdmin.setPassword(admin.getPassword());
		Admin a = adminRepository.save(newAdmin);
		return admin;
	}

	/*
	@Override
	public AdminDTO addAdmin(UserDTO admin) throws InvalidDataException{
		Optional<Admin> optionalAdmin = adminRepository.findById(Integer.valueOf(admin.getId()));
		if(optionalAdmin.isPresent()) {
			throw new InvalidDataException("Service.ADMIN FOUND");
		}
		AdminDTO newAdminDTO = new AdminDTO();
		Admin newAdmin = new Admin();
		return newAdminDTO;
	}
	*/

	/**
	 * Removes the admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public AdminDTO removeAdmin(AdminDTO admin) throws InvalidDataException{
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		AdminDTO newAdminDTO = new AdminDTO(newAdmin.getId(), newAdmin.getPassword());
		adminRepository.deleteById(admin.getId());
		return newAdminDTO;
	}
	
	/**
	 * Removes the admin.
	 *
	 * @param id the id
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public AdminDTO removeAdmin(int id) throws InvalidDataException {
		
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		AdminDTO newAdminDTO = new AdminDTO(newAdmin.getId(), newAdmin.getPassword());
		adminRepository.deleteById(id);
		return newAdminDTO;
	}

	/*
	@Override
	public AdminDTO removeAdmin(UserDTO admin) throws InvalidDataException{
		// TODO Auto-generated method stub
		return null;
	}
	*/

	/**
	 * Update admin.
	 *
	 * @param admin the admin
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public AdminDTO updateAdmin(AdminDTO admin) throws InvalidDataException{
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		newAdmin.setPassword(admin.getPassword());
		return admin;
	}

	/**
	 * Gets the admin.
	 *
	 * @param id the admin Id
	 * @return the admin DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public AdminDTO showAdmin(Integer id) throws InvalidDataException {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		AdminDTO admin = new AdminDTO(newAdmin.getId(), newAdmin.getPassword());
		return admin;
	}

	/*
	@Override
	public AdminDTO updateAdmin(UserDTO admin) throws InvalidDataException{
		// TODO Auto-generated method stub
		return null;
	}
	*/

}
