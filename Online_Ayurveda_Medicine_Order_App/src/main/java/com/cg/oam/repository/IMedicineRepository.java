package com.cg.oam.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.entity.Medicine;


public interface IMedicineRepository extends CrudRepository<Medicine,Integer>{
    public List<Medicine> findByMedicineId(Integer MedcineId);
	
}
