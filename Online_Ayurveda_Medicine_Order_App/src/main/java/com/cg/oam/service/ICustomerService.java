package com.cg.oam.service;

import java.util.List;
import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.exception.InvalidDataException;


public interface ICustomerService{
	
	public Integer addCustomer(CustomerDTO customer)throws InvalidDataException ;
	public void updateCustomer(CustomerDTO customer) throws InvalidDataException ;
	public List<CustomerDTO>  showAllCustomers() throws InvalidDataException ;
	public void deleteCustomer(Integer customerId) throws InvalidDataException;
	public CustomerDTO viewCustomer(Integer customerId) throws InvalidDataException;

}