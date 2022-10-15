package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.Admin;

public interface IAdminRepository extends CrudRepository<Admin, Integer>{
	
	List<Admin> findByPassword(String password);	
}
