package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.exception.InvalidDataException;

public interface IMedicineService {
    /**
     * @param medicineDTO
     * @return
     * @throws InvalidDataException
     */
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) throws InvalidDataException;
    /**
     * @param medicineDTO
     * @return
     * @throws InvalidDataException
     */
    public MedicineDTO viewMedicine(MedicineDTO medicineDTO) throws InvalidDataException;
    /**
     * @param medicineDTO
     * @return
     * @throws InvalidDataException
     */
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO) throws InvalidDataException ;
    /**
     * @param medicineDTO
     * @return
     * @throws InvalidDataException
     */
    public MedicineDTO deleteMedicine(MedicineDTO medicineDTO) throws InvalidDataException ;
    /**
     * @return
     * @throws InvalidDataException
     */
    public List<MedicineDTO> showAllMedicine() throws InvalidDataException ;

    public MedicineDTO viewMedicine(String medicineDTO) throws InvalidDataException;
    public MedicineDTO deleteMedicine(String medicineId) throws InvalidDataException;
    // public MedicineDTO updateMedicine(String medicineId) throws InvalidDataException;

}
