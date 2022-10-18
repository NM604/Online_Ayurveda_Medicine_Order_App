package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.ICustomerService;
import com.cg.oam.dto.CustomerDTO;





/**
 * The Class CustomerAPI.
 */
@RestController
@RequestMapping(value = "/oam/userinterface")
@Validated
public class CustomerAPI {
	
	
	/** The customer service. */
	@Autowired
	private ICustomerService customerService;

	
	/** The environment. */
	@Autowired
	private Environment environment;
	
	
	/**
	 * Gets the all customers.
	 *
	 * @return the all customers
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws InvalidDataException {
		List<CustomerDTO> customerList = customerService.showAllCustomers();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}


	/**
	 * Gets the customer.
	 *
	 * @param customerId the customer id
	 * @return the customer
	 * @throws InvalidDataException the invalid data exception
	 */
	@GetMapping(value = "/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@Min(value=1,message="Id should be greater than or equal to 1")@PathVariable Integer customerId) throws InvalidDataException {
		CustomerDTO customer = customerService.viewCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	
	/**
	 * Adds the customer.
	 *
	 * @param customer the customer
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PostMapping(value = "/customers")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerDTO customer) throws InvalidDataException {
		Integer customerId = customerService.addCustomer(customer);
		String successMessage = environment.getProperty("API.CUSTOMER_INSERT_SUCCESS") + customerId;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	
	/**
	 * Update customer.
	 *
	 * @param customerId the customer id
	 * @param customer the customer
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@PutMapping(value = "/customers/{customerId}")
	public ResponseEntity<String> updateCustomer(@Min(value=1,message="Id should be greater than or equal to 1")@PathVariable Integer customerId, @RequestBody CustomerDTO customer)
			throws InvalidDataException {
		customerService.updateCustomer(customerId,customer.getCustomerName(),customer.getCustomerPassword());
		String successMessage = environment.getProperty("API.CUSTOMER_UPDATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	
	/**
	 * Delete customer.
	 *
	 * @param customerId the customer id
	 * @return the response entity
	 * @throws InvalidDataException the invalid data exception
	 */
	@DeleteMapping(value = "/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@Min(value=1,message="Id should be greater than or equal to 1")@PathVariable Integer customerId) throws InvalidDataException {
		customerService.deleteCustomer(customerId);
		String successMessage = environment.getProperty("API.CUSTOMER_DELETE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
