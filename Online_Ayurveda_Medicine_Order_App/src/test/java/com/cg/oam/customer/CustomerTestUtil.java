package com.cg.oam.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@Test
	@DisplayName("Check Adding Duplicate Customer")
	public void addDuplicateCustomer() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		Customer dupCustomer = new Customer(3,"sharath","shask",null,null);
		
		List<Customer> customers = new ArrayList<>();
		List<Customer> dupCustomers = new ArrayList<>();
		
		
		dupCustomers.add(dupCustomer);
		
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customers);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(dupCustomer);
		iCustomerService.addCustomer(customer);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(dupCustomers);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> iCustomerService.addCustomer(customer));
		Assertions.assertEquals("Service.CUSTOMER_FOUND", e.getMessage());
	}

	@Test
	@DisplayName("Check View Customers By ID")
	public void viewCustomerWithId() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		Optional<Customer> newCustomer = Optional.of(new Customer(3,"sharath","shask",null,null));
		//List<Customer> customers = new ArrayList<>();
		Mockito.when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(newCustomer);
		//Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(newCustomer);
		Assertions.assertEquals(customer.getCustomerId(), iCustomerService.viewCustomer(customer.getCustomerId()).getCustomerId());
		
		
	}
	/*
	
	@Test
	@DisplayName("Check View Customers By Invalid Id")
	public void viewCustomersWithInvalidId() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		Optional<Customer> newCustomer = Optional.of(new Customer(3,"sharath","shask",null,null));
		Mockito.when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(newCustomer);
		Mockito.when(iCustomerRepository.findById(1)).thenReturn(newCustomer);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> iCustomerService.addCustomer(customer));
		Assertions.assertEquals("Service.CUSTOMER_FOUND", e.getMessage());
		
		
	}
	/*
	@Test
	@DisplayName("Check View Customers By Invalid Id")
	public void showAllCustomers() throws InvalidDataException{
		
	}
	*/
	/*
	@Test
	@DisplayName("Testing delete order with valid orderId")
	public void deleteOrderTestWithValidId() throws InvalidDataException {
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		Customer dupCustomer = new Customer(3,"sharath","shask",null,null);
		List<Customer> customers = new ArrayList<>();
		List<Customer> dupCustomers = new ArrayList<>();
		dupCustomers.add(dupCustomer);
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customers);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(dupCustomer);
		//Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword()).thenReturn(customers);
		iCustomerService.addCustomer(customer);
		iCustomerService.deleteCustomer(customer.getCustomerId());
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(dupCustomers);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> iCustomerService.deleteCustomer(customer.getCustomerId()));
		Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", e.getMessage());

	}
	*/


}
