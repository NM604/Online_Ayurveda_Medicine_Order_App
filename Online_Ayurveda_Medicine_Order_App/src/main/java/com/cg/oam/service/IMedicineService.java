package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.exception.InvalidDataException;

public interface IMedicineService {
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) throws InvalidDataException;
    public MedicineDTO viewMedicine(MedicineDTO medicineDTO) throws InvalidDataException;
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO) throws InvalidDataException ;
    public MedicineDTO deleteMedicine(MedicineDTO medicineDTO) throws InvalidDataException ;
    public List<MedicineDTO> showAllMedicine() throws InvalidDataException ;

}
