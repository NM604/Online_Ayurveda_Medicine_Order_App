package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer>{
	
	List<User> findByuserName(String userName);
}
