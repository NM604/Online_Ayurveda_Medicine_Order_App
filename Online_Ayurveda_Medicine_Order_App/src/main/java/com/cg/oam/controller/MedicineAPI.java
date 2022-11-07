package com.cg.oam.controller;

import java.util.*;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IMedicineService;
import com.cg.oam.dto.MedicineDTO;

@RestController
@RequestMapping(value = "/oam/userinterface")
public class MedicineAPI {

    @Autowired
    private IMedicineService iMedicineService;

    @Autowired
    private Environment environment;

    
    /** 
     * @return ResponseEntity<List<MedicineDTO>>
     * @throws InvalidDataException
     */
    @GetMapping(value = "/medicine")
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() throws InvalidDataException{
        List<MedicineDTO> medicineDTOs = iMedicineService.showAllMedicine();
        return new ResponseEntity<List<MedicineDTO>>(medicineDTOs, HttpStatus.OK);
    }

    
    /** 
     * @param inputMedicineDTO
     * @return ResponseEntity<MedicineDTO>
     * @throws InvalidDataException
     */
    @GetMapping(value = "/medicine/{medicineId}")
    public ResponseEntity<MedicineDTO> getMedicine(@PathVariable @Min(value = 1, message = "please give valid id") Integer medicineId) throws InvalidDataException{
        MedicineDTO medicineDTO = iMedicineService.viewMedicine(medicineId);
        return new ResponseEntity<MedicineDTO>(medicineDTO,HttpStatus.OK);
    }

    
    /** 
     * @param newMedicineDTO
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @PostMapping(value = "/medicine")
    public ResponseEntity<String> addMedicine(@RequestBody MedicineDTO newMedicineDTO) throws InvalidDataException{
        iMedicineService.addMedicine(newMedicineDTO);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") + newMedicineDTO.getMedicineId();
        return new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
    }

    
    /** 
     * @param newmeMedicineDTO
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @PutMapping(value = "/medicine/{medicineId}")
    public ResponseEntity<String> updateMedicine(@PathVariable @Min(value = 1, message = "please give valid id") String medicineId,@RequestBody MedicineDTO medicineDTO) throws InvalidDataException{
        iMedicineService.updateMedicine(medicineDTO);
        String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
        return new ResponseEntity<String>(successMessage,HttpStatus.OK);
    }
    
    /** 
     * @param medicineDTO
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @DeleteMapping(value = "/medicine/{medicineId}")
    public ResponseEntity<String> deleleMedicine(@PathVariable @Min(value = 1, message = "please give valid id") Integer medicineId ) throws InvalidDataException{
        iMedicineService.deleteMedicine(medicineId);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<String>(successMessage,HttpStatus.OK);
    }
    
}
