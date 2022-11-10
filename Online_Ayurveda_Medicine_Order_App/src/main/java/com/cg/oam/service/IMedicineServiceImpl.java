package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.dto.MedicineDTO;
import com.cg.oam.entity.Category;
import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IMedicineRepository;
import com.cg.oam.repository.ICategoryRepository;



@Service(value = "iMedicineService")
@Transactional
public class IMedicineServiceImpl implements IMedicineService {

    @Autowired
    private IMedicineRepository iMedicineRepository;

    @Autowired
    private ICategoryRepository iCategoryRepository; 

    

    
    /** 
     * @param medicine
     * @return MedicineDTO
     */
    public MedicineDTO convertEntityToDto(Medicine medicine) {
		MedicineDTO resultOMedicineDto = new MedicineDTO();
        // resultOMedicineDto.setSrno(medicine.getSrno());
		resultOMedicineDto.setMedicineId(medicine.getMedicineId()); 
		resultOMedicineDto.setMedicineName(medicine.getMedicineName());
		resultOMedicineDto.setMedicineCost(medicine.getMedicineCost());
		resultOMedicineDto.setMfd(medicine.getMfd());
		resultOMedicineDto.setExpiryDate(medicine.getExpiryDate());
        resultOMedicineDto.setCompanyName(medicine.getCompanyName());
        resultOMedicineDto.setCategoryDTO(new CategoryDTO(medicine.getCategory().getCategoryId(),medicine.getCategory().getCategoryName()));

		return resultOMedicineDto;
	}

    
    /** 
     * @param medicineDTO
     * @return MedicineDTO
     * @throws InvalidDataException
     */
    @Override
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) throws InvalidDataException {
        // TODO Auto-generated method stub

        List<Medicine> optionalMedicines = iMedicineRepository.findByMedicineId(medicineDTO.getMedicineId());
        if(!optionalMedicines.isEmpty()){
            throw new InvalidDataException("Service.MEDICINE_FOUND");
        }

        Medicine medicineEntity = new Medicine();
        // medicineEntity.setMedicineId(medicineDTO.getMedicineId());
        medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        medicineEntity.setMedicineCost(medicineDTO.getMedicineCost());
        medicineEntity.setMfd(medicineDTO.getMfd());
        medicineEntity.setExpiryDate(medicineDTO.getExpiryDate());
        medicineEntity.setCompanyName(medicineDTO.getCompanyName());
        List<Category> categoryEntity = iCategoryRepository.findByCategoryName(medicineDTO.getCategoryDTO().getCategoryName());
        if (categoryEntity.isEmpty()){
            Category newcat =new Category(medicineDTO.getCategoryDTO().getCategoryId(),medicineDTO.getCategoryDTO().getCategoryName());
            iCategoryRepository.save(newcat);
            medicineEntity.setCategory(newcat);
        }
        else{
            Category cat = categoryEntity.get(0);
            medicineEntity.setCategory(cat);
        }
        
        // medicineEntity.setCategory(new Category(medicineDTO.getCategoryDTO().getCategoryId(),medicineDTO.getCategoryDTO().getCategoryName()));
        
        Medicine createdMedicine  = iMedicineRepository.save(medicineEntity);

        MedicineDTO createdMedicineDTO = convertEntityToDto(createdMedicine);

        return createdMedicineDTO;
    }

    
    /** 
     * @param medicineDTO
     * @return MedicineDTO
     * @throws InvalidDataException
     */
    // @Override
    // public MedicineDTO viewMedicine(MedicineDTO medicineDTO) throws InvalidDataException {
    //     // TODO Auto-generated method stub
        
    //     List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineDTO.getMedicineId());
    //     if(optional.isEmpty()){
    //         throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
    //     }
	// 	Medicine medicineEntity = optional.get(0);

	// 	MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

    //     return createdMedicineDTO;
    // }
    @Override
    public MedicineDTO viewMedicine(Integer medicineId) throws InvalidDataException {
        // TODO Auto-generated method stub
        
        List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineId);
        if(optional.isEmpty()){
            throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
        }
		Medicine medicineEntity = optional.get(0);

		MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

        return createdMedicineDTO;
    }

    
    /** 
     * @param medicineDTO
     * @return MedicineDTO
     * @throws InvalidDataException
     */
    @Override
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO) throws InvalidDataException {
        // TODO Auto-generated method stub
        // List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineDTO.getMedicineId());
        Optional<Medicine> optional = iMedicineRepository.findById(medicineDTO.getMedicineId());
        if(optional.isEmpty()){
            throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
        }
		Medicine medicineEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_ITEM_NOT_FOUND"));
        // if (medicineEntity.getMedicineId()!=null)
        //     medicineEntity.setMedicineId(medicineDTO.getMedicineId());
        // if(medicineEntity.getMedicineName()!=null)
			medicineEntity.setMedicineName(medicineDTO.getMedicineName());
        // if(medicineEntity.getMedicineCost()!=null)
			medicineEntity.setMedicineCost(medicineDTO.getMedicineCost());
        // if(medicineEntity.getMfd()!=null)
			medicineEntity.setMfd(medicineDTO.getMfd());
        // if(medicineEntity.getExpiryDate()!=null)
			medicineEntity.setExpiryDate(medicineDTO.getExpiryDate());
        // if(medicineEntity.getCompanyName()!=null)
			medicineEntity.setCompanyName(medicineDTO.getCompanyName());
        // if(medicineEntity.getCategory()!=null)
		// 	medicineEntity.setCategory(medicineEntity.getCategory());

            MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

            return createdMedicineDTO;

    }
    // @Override
    // public MedicineDTO updateMedicine(String medicineId) throws InvalidDataException {
    //     // TODO Auto-generated method stub
    //     List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineId);
    //     if(optional.isEmpty()){
    //         throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
    //     }
	// 	Medicine medicineEntity = optional.get(0);

    //     if (medicineEntity.getMedicineId()!=null)
    //         medicineEntity.setMedicineId(medicineEntity.getMedicineId());
    //     if(medicineEntity.getMedicineName()!=null)
	// 		medicineEntity.setMedicineName(medicineEntity.getMedicineName());
    //     if(medicineEntity.getMedicineCost()!=null)
	// 		medicineEntity.setMedicineCost(medicineEntity.getMedicineCost());
    //     if(medicineEntity.getMfd()!=null)
	// 		medicineEntity.setMfd(medicineEntity.getMfd());
    //     if(medicineEntity.getExpiryDate()!=null)
	// 		medicineEntity.setExpiryDate(medicineEntity.getExpiryDate());
    //     if(medicineEntity.getCompanyName()!=null)
	// 		medicineEntity.setCompanyName(medicineEntity.getCompanyName());
    //     if(medicineEntity.getCategory()!=null)
	// 		medicineEntity.setCategory(medicineEntity.getCategory());

    //         MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

    //         return createdMedicineDTO;

    // }

    
    /** 
     * @param medicineDTO
     * @return MedicineDTO
     * @throws InvalidDataException
     */
    @Override
    public MedicineDTO deleteMedicine(MedicineDTO medicineDTO) throws InvalidDataException  {
        // TODO Auto-generated method stub
        List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineDTO.getMedicineId());
        if(optional.isEmpty()){
            throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
        }
		Medicine medicineEntity = optional.get(0);

        MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

        
        iMedicineRepository.delete(medicineEntity);
        return createdMedicineDTO;
    }
    @Override
    public MedicineDTO deleteMedicine(Integer medicineId) throws InvalidDataException  {
        // TODO Auto-generated method stub
        List<Medicine> optional = iMedicineRepository.findByMedicineId(medicineId);
        if(optional.isEmpty()){
            throw new InvalidDataException("Service.MEDICINE_NOT_FOUND");
        }
		Medicine medicineEntity = optional.get(0);

        MedicineDTO createdMedicineDTO = convertEntityToDto(medicineEntity);

        
        iMedicineRepository.delete(medicineEntity);
        return createdMedicineDTO;
    }

    
    /** 
     * @return List<MedicineDTO>
     * @throws InvalidDataException
     */
    @Override
    public List<MedicineDTO> showAllMedicine()throws InvalidDataException  {
        // TODO Auto-generated method stub
        Iterable<Medicine> medicines= iMedicineRepository.findAll(); 
        List<MedicineDTO> medicineDTOs = new ArrayList<>();

        medicines.forEach(medicine->{
            MedicineDTO newmeMedicineDTO = new MedicineDTO(medicine.getMedicineId(), medicine.getMedicineName(), medicine.getMedicineCost(), medicine.getMfd(), medicine.getExpiryDate(), medicine.getCompanyName(),  new CategoryDTO(medicine.getCategory().getCategoryId(),medicine.getCategory().getCategoryName()));
            medicineDTOs.add(newmeMedicineDTO);
        });

        return medicineDTOs;
    }
    

}
