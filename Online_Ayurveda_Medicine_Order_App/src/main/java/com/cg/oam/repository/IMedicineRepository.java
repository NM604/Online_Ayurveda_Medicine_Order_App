package com.cg.oam.repository;

import java.util.List;

import com.cg.oam.dto.MedicineDTO;

public interface IMedicineRepository {
	
	public MedicineDTO addMedicine(MedicineDTO medicine);
	
	public MedicineDTO viewMedicine(MedicineDTO medicine);

	public MedicineDTO updateMedicine(MedicineDTO medicine);

	public MedicineDTO deleteMedicine(MedicineDTO medicine);

	public List<MedicineDTO> showAllMedicines(MedicineDTO medicine);
}
