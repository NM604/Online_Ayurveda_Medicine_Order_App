package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.Admin;

/**
 * The Interface IAdminRepository.
 */
public interface IAdminRepository extends CrudRepository<Admin, Integer>{
	
	/**
	 * Find by password.
	 *
	 * @param password the password
	 * @return the list
	 */
	List<Admin> findByPassword(String password);	
}
