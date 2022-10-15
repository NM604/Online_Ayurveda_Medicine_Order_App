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

@Service(value = "customerService")
@Transactional
public class ICustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository iCustomerRepository;
	@Override
	public Integer addCustomer(CustomerDTO customer) throws InvalidDataException  {
		Customer customerEntity = new Customer();
		customerEntity.setCustomerName(customer.getCustomerName());
		customerEntity.setCustomerPassword(customer.getCustomerPassword());
		customerEntity.setMedicineList(customer.getMedicineList());
		customerEntity.setCustomerId(customer.getCustomerId());
		customerEntity.setOrder(customer.getOrder());
		Customer customerEntity2 = iCustomerRepository.save(customerEntity);
		return customerEntity2.getCustomerId();
	}

	@Override
	public void updateCustomer(CustomerDTO customer ) throws InvalidDataException {
		Optional<Customer> customer1 = iCustomerRepository.findById(customer.getCustomerId());
		Customer c = customer1.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		if(c.getCustomerName()!=null) 
			c.setCustomerName(c.getCustomerName());
		if(c.getCustomerPassword()!=null) 
			c.setCustomerPassword(c.getCustomerPassword());
		
	}

	@Override
	public List<CustomerDTO> showAllCustomers() throws InvalidDataException {
		Iterable<Customer> customers = iCustomerRepository.findAll();
		List<CustomerDTO> customers2 = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO cust = new CustomerDTO();
			cust.setCustomerName(customer.getCustomerName());
			cust.setCustomerPassword(customer.getCustomerPassword());
			cust.setMedicineList(customer.getMedicineList());
			cust.setCustomerId(customer.getCustomerId());
			cust.setOrder(customer.getOrder());
			customers2.add(cust);
		});
		if (customers2.isEmpty())
			throw new InvalidDataException("Service.CUSTOMERS_NOT_FOUND");
		return customers2;
	}

	@Override
	public void deleteCustomer(Integer customerId) throws InvalidDataException {
		Optional<Customer> customer1 = iCustomerRepository.findById(customerId);
		customer1.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		iCustomerRepository.deleteById(customerId);
	}

	@Override
	public CustomerDTO viewCustomer(Integer customerId) throws InvalidDataException {
		Optional<Customer> optional = iCustomerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InvalidDataException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customer2 = new CustomerDTO();
		customer2.setCustomerName(customer.getCustomerName());
		customer2.setCustomerPassword(customer.getCustomerPassword());
		customer2.setMedicineList(customer.getMedicineList());
		customer2.setCustomerId(customer.getCustomerId());
		customer2.setOrder(customer.getOrder());
		return customer2;
	}
	

}
