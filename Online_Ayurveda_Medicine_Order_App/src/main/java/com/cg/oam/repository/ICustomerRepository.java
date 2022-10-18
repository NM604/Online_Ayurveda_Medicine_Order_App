package com.cg.oam.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Customer;


//import com.cg.oam.dto.CustomerDTO;
//import com.cg.oam.entity.Customer;

/**
 * The Interface ICustomerRepository.
 */
public interface ICustomerRepository extends CrudRepository<Customer, Integer>{

	/**
	 * Find by customer password.
	 *
	 * @param password the password
	 * @return the list
	 */
	List<Customer> findByCustomerPassword(String password);
}
