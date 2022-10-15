package com.cg.oam.repository;


import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.Medicine;


public interface IMedicineRepository extends CrudRepository<Medicine,String>{
	
}
