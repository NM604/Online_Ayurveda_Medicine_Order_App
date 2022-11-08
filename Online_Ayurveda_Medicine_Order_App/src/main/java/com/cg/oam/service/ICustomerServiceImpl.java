package com.cg.oam.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.repository.ICustomerRepository;


import com.cg.oam.entity.Customer;
import com.cg.oam.exception.InvalidDataException;


/**
 * The Class ICustomerServiceImpl.
 */
@Service(value = "customerService")
@Transactional
public class ICustomerServiceImpl implements ICustomerService{
	
	/** The i customer repository. */
	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	/**
	 * Adds the customer.
	 *
	 * @param customer the customer
	 * @return the integer
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public Integer addCustomer(CustomerDTO customer) throws InvalidDataException  {
		List<Customer> optionalCustomer = iCustomerRepository.findByCustomerName(customer.getCustomerName());
		if(!optionalCustomer.isEmpty()) {
			throw new InvalidDataException("Service.CUSTOMER_FOUND");
		}
		Customer customerEntity = new Customer();
		customerEntity.setCustomerName(customer.getCustomerName());
		customerEntity.setCustomerPassword(customer.getCustomerPassword());
		customerEntity.setCustomerId(customer.getCustomerId());
		Customer customerEntity2 = iCustomerRepository.save(customerEntity);
		return customerEntity2.getCustomerId();
	}

	/**
	 * Update customer.
	 *
	 * @param customer the customer
	 * @return the customer DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer) throws InvalidDataException {
		Optional<Customer> customer1 = iCustomerRepository.findById(customer.getCustomerId());
		Customer c = customer1.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		c.setCustomerName(customer.getCustomerName());
		c.setCustomerPassword(customer.getCustomerPassword());
		return customer;
		
	}

	/**
	 * Show all customers.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<CustomerDTO> showAllCustomers() throws InvalidDataException {
		Iterable<Customer> customers = iCustomerRepository.findAll();
		List<CustomerDTO> customers2 = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO cust = new CustomerDTO();
			cust.setCustomerName(customer.getCustomerName());
			cust.setCustomerPassword(customer.getCustomerPassword());
			cust.setCustomerId(customer.getCustomerId());
			customers2.add(cust);
		});
		if (customers2.isEmpty())
			throw new InvalidDataException("Service.CUSTOMERS_NOT_FOUND");
		return customers2;
	}

	/**
	 * Delete customer.
	 *
	 * @param customerId the customer id
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public CustomerDTO deleteCustomer(Integer customerId) throws InvalidDataException {
		Optional<Customer> customer1 = iCustomerRepository.findById(customerId);
		Customer cus=customer1.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customer=new CustomerDTO(cus.getCustomerId(),cus.getCustomerName(),cus.getCustomerName());
		iCustomerRepository.deleteById(customerId);
		return customer;
	}

	/**
	 * View customer.
	 *
	 * @param customerId the customer id
	 * @return the customer DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public CustomerDTO viewCustomer(Integer customerId) throws InvalidDataException {
		Optional<Customer> optional = iCustomerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setCustomerName(customer.getCustomerName());
		customer2.setCustomerPassword(customer.getCustomerPassword());
		customer2.setCustomerId(customer.getCustomerId());
		return customer2;
	}
	
	public boolean validateCustomer(String userName, String password) throws InvalidDataException{
		List<Customer> customers = iCustomerRepository.findByCustomerName(userName);
		if(customers.isEmpty()) {
			throw new InvalidDataException("Service.CUSTOMER_NOT_FOUND");
		}
		Customer customer = customers.get(0);
		if(!password.equals(customer.getCustomerPassword())) {
			throw new InvalidDataException("Service.INVALID_CREDENTIALS");
		}
		return true;
	}

}
