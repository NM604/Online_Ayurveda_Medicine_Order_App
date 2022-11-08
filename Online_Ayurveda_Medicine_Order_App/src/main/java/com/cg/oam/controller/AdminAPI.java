package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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


/**
 * The Class AdminAPI.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value="/oam/administrator")
@Validated
public class AdminAPI {
	
	/** The admin service. */
	@Autowired
	IAdminService adminService;
	
	/** The environment. */
	@Autowired
	Environment environment;
	
	/**
	 * Gets the all admins.
	 *
	 * @return the all admins
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/admins")
	public ResponseEntity<List<AdminDTO>> getAllAdmins() throws InvalidDataException {
		List<AdminDTO> admins = adminService.showAllAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	/**
	 * Gets an admin with Id.
	 *
	 * @return the admin with requested Id
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/admin/{Id}")
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable 
													@Min(value = 1, message = "Admin ID should be greater than 0") Integer Id) 
															throws InvalidDataException {
		AdminDTO admin = adminService.showAdmin(Id);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	/**
	 * Adds the admin.
	 *
	 * @param admin the admin
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PostMapping(value = "/admin")
	public ResponseEntity<String> addAdmin(@Valid @RequestBody AdminDTO admin) throws InvalidDataException{
		adminService.addAdmin(admin);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + admin.getId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	/**
	 * Update admin.
	 *
	 * @param adminId the admin id
	 * @param admin the admin
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PutMapping(value = "/admin/{adminId}")
	public ResponseEntity<String> updateAdmin(@PathVariable 
												@Min(value = 1, message = "Admin ID should be greater than 0") Integer adminId, 
												@Valid @RequestBody AdminDTO admin)
														throws InvalidDataException{
		adminService.updateAdmin(admin);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	/**
	 * Removes the admin.
	 *
	 * @param adminId - the admin id
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@DeleteMapping(value = "/admin/{adminId}")
	public ResponseEntity<String> removeAdmin(@PathVariable 
												@Min(value = 1, message = "Admin ID should be greater than 0") Integer adminId) 
														throws InvalidDataException{
		AdminDTO admin = new AdminDTO(adminId,"TEST_VALUE");
		adminService.removeAdmin(admin);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	/**
	 * Validate admin.
	 *
	 * @param admin - the admin
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PostMapping(value = "/adminvalidate")
	public ResponseEntity<String> validateAdmin(@Valid @RequestBody AdminDTO admin) throws InvalidDataException{
		String successMessage = "";
		if (adminService.validateAdmin(admin.getId(),admin.getPassword())) {
			successMessage += "Valid Credentials";
		}
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
