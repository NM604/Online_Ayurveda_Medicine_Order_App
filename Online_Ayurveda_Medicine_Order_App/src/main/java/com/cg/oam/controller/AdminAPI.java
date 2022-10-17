package com.cg.oam.controller;

import java.util.List;

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

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IAdminService;


@RestController
@RequestMapping(value="/oam/administrator")
public class AdminAPI {
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/admins")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() throws InvalidDataException {
		List<AdminDTO> admins = adminService.showAllAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin")
	public ResponseEntity<String> addAdmin(@RequestBody AdminDTO admin) throws InvalidDataException{
		adminService.addAdmin(admin);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + admin.getId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/admin/{adminId}")
	public ResponseEntity<String> updateAdmin(@PathVariable Integer adminId, @RequestBody AdminDTO admin)
			throws InvalidDataException{
		adminService.updateAdmin(admin);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/admin/{adminId}")
	public ResponseEntity<String> removeAdmin(@PathVariable Integer adminId) throws InvalidDataException{
		AdminDTO admin = new AdminDTO(adminId,"TEST_VALUE");
		adminService.removeAdmin(admin);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PostMapping(value = "/adminvalidate")
	public ResponseEntity<String> validateAdmin(@RequestBody AdminDTO admin) throws InvalidDataException{
		String successMessage = "";
		if (adminService.validateAdmin(admin.getId(),admin.getPassword())) {
			successMessage += "Valid Credentials";
		}
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
