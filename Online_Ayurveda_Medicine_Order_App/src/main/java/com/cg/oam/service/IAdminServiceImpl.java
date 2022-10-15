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

@Service(value="adminService")
@Transactional
public class IAdminServiceImpl implements IAdminService{
	
	@Autowired
	private IAdminRepository adminRepository;

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

	@Override
	public boolean validateAdmin(String id, String password) throws InvalidDataException {
		Optional<Admin> optionalAdmin = adminRepository.findById(id);
		Admin admin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		if(!password.equals(admin.getPassword())) {
			throw new InvalidDataException("Service.INVALID_CREDENTIALS");
		}
		return true;
	}

	@Override
	public AdminDTO addAdmin(AdminDTO admin) throws InvalidDataException {
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
		if(optionalAdmin.isPresent()) {
			throw new InvalidDataException("Service.ADMIN FOUND");
		}
		Admin newAdmin = new Admin();
		newAdmin.setId(admin.getId());
		newAdmin.setPassword(admin.getPassword());
		adminRepository.save(newAdmin);
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

	@Override
	public AdminDTO removeAdmin(AdminDTO admin) throws InvalidDataException{
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		adminRepository.deleteById(admin.getId());
		return admin;
	}
	
	@Override
	public AdminDTO removeAdmin(int id) throws InvalidDataException {
		Optional<Admin> optionalAdmin = adminRepository.findById(String.valueOf(id));
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		AdminDTO newAdminDTO = new AdminDTO(newAdmin.getId(), newAdmin.getPassword());
		adminRepository.deleteById(String.valueOf(id));
		return newAdminDTO;
	}

	/*
	@Override
	public AdminDTO removeAdmin(UserDTO admin) throws InvalidDataException{
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public AdminDTO updateAdmin(AdminDTO admin) throws InvalidDataException{
		Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());
		Admin newAdmin = optionalAdmin.orElseThrow(() -> new InvalidDataException("Service.ADMIN_NOT_FOUND"));
		newAdmin.setPassword(admin.getPassword());
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
