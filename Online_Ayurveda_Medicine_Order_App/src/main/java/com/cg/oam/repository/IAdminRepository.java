package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.Admin;
import com.cg.oam.entity.User;

public interface IAdminRepository extends CrudRepository<Admin, String>{
	
}
