package com.cg.oam.customer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.UserDTO;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.User;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.service.ICustomerServiceImpl;

@SpringBootTest
@DisplayName("Validations for Customer Entity")
public class CustomerTestUtil {
	
	@Mock
	ICustomerRepository iCustomerRepository;
	
	@InjectMocks
	ICustomerServiceImpl iCustomerService;
	
	@Test
	@DisplayName("Check Adding New Customer")
	public void addCustomer() throws InvalidDataException{		
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		List<Customer> customers = new ArrayList<>();
		Customer newCustomer = new Customer(3,"sharath","shask",null,null);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customers);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(newCustomer);
		Assertions.assertEquals(customer.getCustomerId(), iCustomerService.addCustomer(customer));
	}
	/*
	
	@Test
	@DisplayName("Check Adding Duplicate Customer")
	public void addDuplicateCustomer() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		Customer dupCustomer = new Customer(3,"sharath","shask",null,null);
		
		List<Customer> customers = new ArrayList<>();
		List<Customer> dupCustomers = new ArrayList<>();
		
		
		dupCustomers.add(dupCustomer);
		Customer newCustomer = new Customer(3,"sharath","shask",null,null);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customers);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(newCustomer);
		iCustomerService.addCustomer(customer);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(dupCustomers);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> iCustomerService.addCustomer(customer));
		Assertions.assertEquals("Service.CUSTOMER_FOUND", e.getMessage());
	}
	*/

}
