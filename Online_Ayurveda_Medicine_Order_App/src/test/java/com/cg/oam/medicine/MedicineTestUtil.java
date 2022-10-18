package com.cg.oam.medicine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.entity.Category;
import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IMedicineRepository;
import com.cg.oam.service.IMedicineServiceImpl;

@SpringBootTest
@DisplayName(value = "Validations of Medicine entity ")
public class MedicineTestUtil {
    

    @Mock
    IMedicineRepository medicineRepository;

    @InjectMocks
    IMedicineServiceImpl medicineService;

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Check adding new medicine")
    public void addMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);

        Category category = new Category("a", "heart");
        List<Medicine> medicines = new ArrayList<>();
        Medicine newMedicine = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", category);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicines);
        Mockito.when(medicineRepository.save(Mockito.any())).thenReturn(newMedicine);
        Assertions.assertEquals(medicineDTO, medicineService.addMedicine(medicineDTO));

    }


    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "check addding for adding a duplicate medicine")
    public void addDuplicateMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);

        Category dupCategory = new Category("a", "heart");
        Medicine dupMedicine = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", dupCategory);

        List<Medicine> medicines = new ArrayList<>();
		List<Medicine> dupMedicines = new ArrayList<>();

        dupMedicines.add(dupMedicine);
        Medicine newMedicine = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", dupCategory);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicines);
        Mockito.when(medicineRepository.save(Mockito.any())).thenReturn(newMedicine);
        medicineService.addMedicine(medicineDTO);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(dupMedicines);
        InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, ()->medicineService.addMedicine(medicineDTO));
        Assertions.assertEquals("Service.MEDICINE_FOUND", e.getMessage());

    }

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Check Updating Exisisting medicine")
    public void updateExistingMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);

        CategoryDTO dupCategory = new CategoryDTO("a", "heart");
        MedicineDTO dupMedicineDTO = new MedicineDTO(1, "101", "paracitamol", 570f, LocalDate.now(), LocalDate.now(), "AB", dupCategory);

        List<Medicine> medicines = new ArrayList<>();
		List<Medicine> updateMedicines = new ArrayList<>();


        Category category = new Category("a", "heart");
        Medicine newMed = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", category);
        updateMedicines.add(newMed);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicines);
        Mockito.when(medicineRepository.save(Mockito.any())).thenReturn(newMed);
        medicineService.addMedicine(medicineDTO);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(updateMedicines);
        Assertions.assertEquals(dupMedicineDTO, medicineService.updateMedicine(dupMedicineDTO));

    }
    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Check Updating non-Exisisting medicine")
        public void updatingNonExistingMedicine() throws InvalidDataException{

        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);
        
        InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, ()->medicineService.updateMedicine(medicineDTO));
        Assertions.assertEquals("Service.MEDICINE_NOT_FOUND", e.getMessage());
    }

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Check viewing medicine for existing medicine")
    public void viewForExistingMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);
        List<Medicine> medicines = new ArrayList<>();
        
        Category category1 = new Category("a", "heart");
        Medicine medicine = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", category1);
        List<Medicine> medicineDemo = new ArrayList<>();
        medicineDemo.add(medicine);
        
        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicines);
        Mockito.when(medicineRepository.save(Mockito.any())).thenReturn(medicine);
        medicineService.addMedicine(medicineDTO);
        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicineDemo);
        Assertions.assertEquals(medicineDTO, medicineService.viewMedicine(medicineDTO));
    }

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Check viewing medicine for non-existing medicine")
    public void viewForNonExistingMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);
        InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, ()->medicineService.viewMedicine(medicineDTO));
        Assertions.assertEquals("Service.MEDICINE_NOT_FOUND", e.getMessage());
    }

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "check deleting non-existing Medicine")
    public void deleteNonExistingMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);
        InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, ()->medicineService.deleteMedicine(medicineDTO));
        Assertions.assertEquals("Service.MEDICINE_NOT_FOUND", e.getMessage());
    }

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "Delete existing medicine")
    public void deleteExistingMedicine() throws InvalidDataException{
        CategoryDTO categoryDTO = new CategoryDTO("a", "heart");
        MedicineDTO medicineDTO = new MedicineDTO(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", categoryDTO);

        List<Medicine> medicine = new ArrayList<>();

        Category category1 = new Category("a", "heart");
        Medicine medicineEntity = new Medicine(1, "101", "paracitamol", 500f, LocalDate.now(), LocalDate.now(), "AB", category1);

        List<Medicine> medicineList  = new ArrayList<>();
        medicineList.add(medicineEntity);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicine);
        Mockito.when(medicineRepository.save(Mockito.any())).thenReturn(medicineEntity);
        medicineService.addMedicine(medicineDTO);

        Mockito.when(medicineRepository.findByMedicineId(medicineDTO.getMedicineId())).thenReturn(medicineList);
        Assertions.assertEquals(medicineDTO, medicineService.deleteMedicine(medicineDTO));
        Mockito.verify(medicineRepository).delete(medicineEntity);

    }
}

    

