package com.cg.oam.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.dto.UserDTO;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Order;
import com.cg.oam.entity.User;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.service.ICustomerServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerTestUtil.
 */
@SpringBootTest
@DisplayName("Validations for Customer Entity")
public class ICustomerServiceTest {
	
	/** The i customer repository. */
	@Mock
	ICustomerRepository iCustomerRepository;
	
	/** The i customer service. */
	@InjectMocks
	ICustomerServiceImpl iCustomerService;
	
	/**
	 * Adds the customer.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Adds the duplicate customer.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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

	/**
	 * View customer with id.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
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
	
	/**
	 * Update customer.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Existing Customer")
	public void updateCustomer() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		CustomerDTO customer1 = new CustomerDTO(3,"sharath","shask",null,null);
		Optional<Customer> dupCustomer = Optional.of(new Customer(3,"sharath","shask",null,null));
		
		List<Customer> customers = new ArrayList<>();
		
		Customer newCustomer = new Customer(3,"sharath","shask",null,null);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customers);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(newCustomer);
		iCustomerService.addCustomer(customer);
		
		Mockito.when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(dupCustomer);
		Assertions.assertEquals(customer1, iCustomerService.updateCustomer(customer1));
		}
	
	/**
	 * Update customer not present.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Updating Non-Existant Customer")
	public void updateCustomerNotPresent() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> iCustomerService.updateCustomer(customer));
		Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", e.getMessage());	
	}
	
	/**
	 * Delete customer.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check Removing Existing Customer")
	public void deleteCustomer() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		List<Customer> customerList = new ArrayList<>();
		Customer newCustomer = new Customer(3,"sharath","shask",null,null);
		
		Mockito.when(iCustomerRepository.findByCustomerPassword(customer.getCustomerPassword())).thenReturn(customerList);
		Mockito.when(iCustomerRepository.save(Mockito.any())).thenReturn(newCustomer);
		iCustomerService.addCustomer(customer);
		
		Mockito.when(iCustomerRepository.findById(customer.getCustomerId())).thenReturn(Optional.of(newCustomer));
		Assertions.assertEquals(customer.getCustomerId(), iCustomerService.deleteCustomer(3).getCustomerId());
		Mockito.verify(iCustomerRepository).deleteById(3);
	}
	
	/**
	 * Delete customer not present.
	 */
	@Test
	@DisplayName("Check Removing Non-Existant Customer")
	public void deleteCustomerNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () ->  iCustomerService.deleteCustomer(2));
		Assertions.assertEquals("Service.CUSTOMER_NOT_FOUND", e.getMessage());
	}
	
	/**
	 * Show all customers.
	 *
	 * @throws InvalidDataException the invalid data exception
	 */
	@Test
	@DisplayName("Check View Customers By Invalid Id")
	public void showAllCustomers() throws InvalidDataException{
		CustomerDTO customer = new CustomerDTO(3,"sharath","shask",null,null);
		List<CustomerDTO> expCusts =new ArrayList<>();
		expCusts.add(customer);
		List<Customer> customers =new ArrayList<>();
		Customer cus=new Customer();
		cus.setCustomerId(3);;
		customers.add(cus);
		Mockito.when(iCustomerRepository.findAll()).thenReturn(customers);
		List<CustomerDTO> actualCusts = iCustomerService.showAllCustomers();
		assertEquals(expCusts.get(0).getCustomerId(), actualCusts.get(0).getCustomerId());
		
	}
	
	


}
