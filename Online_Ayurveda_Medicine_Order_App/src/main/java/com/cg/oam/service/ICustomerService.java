package com.cg.oam.service;

import java.util.List;
import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.exception.InvalidDataException;



/**
 * The Interface ICustomerService.
 */
public interface ICustomerService{
	
	/**
	 * Adds the customer.
	 *
	 * @param customer the customer
	 * @return the integer
	 * @throws InvalidDataException the invalid data exception
	 */
	public Integer addCustomer(CustomerDTO customer)throws InvalidDataException ;
	
	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 * @return the customer DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public CustomerDTO updateCustomer(CustomerDTO customer) throws InvalidDataException ;
	
	/**
	 * Show all customers.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<CustomerDTO>  showAllCustomers() throws InvalidDataException ;
	
	/**
	 * Delete customer.
	 *
	 * @param customerId the customer id
	 * @throws InvalidDataException the invalid data exception
	 */
	public CustomerDTO deleteCustomer(Integer customerId) throws InvalidDataException;
	
	/**
	 * View customer.
	 *
	 * @param customerId the customer id
	 * @return the customer DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public CustomerDTO viewCustomer(Integer customerId) throws InvalidDataException;

}