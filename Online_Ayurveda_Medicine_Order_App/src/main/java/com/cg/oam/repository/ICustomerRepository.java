package com.cg.oam.repository;

import java.util.List;

import com.cg.oam.dto.CustomerDTO;

public interface ICustomerRepository {
	
	public CustomerDTO addCustomer(CustomerDTO customer);
	
	public CustomerDTO updateCustomer(CustomerDTO customer);
	
	public CustomerDTO viewCustomer(CustomerDTO customer);
	
	public CustomerDTO deleteCustomer(CustomerDTO customer);
	
	public List<CustomerDTO> showAllCustomers();

}
