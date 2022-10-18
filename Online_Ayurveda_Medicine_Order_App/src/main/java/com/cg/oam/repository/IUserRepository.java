package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.User;

/**
 * The Interface IUserRepository.
 */
public interface IUserRepository extends CrudRepository<User, Integer>{
	
	/**
	 * Find byuser name.
	 *
	 * @param userName the user name
	 * @return the list
	 */
	List<User> findByuserName(String userName);
}
