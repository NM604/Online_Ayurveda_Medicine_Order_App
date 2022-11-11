package com.cg.oam.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class MedicineDTO {

	// private Integer srno;

	// @NotNull(message = "Please provide medicineId")
	private Integer medicineId;
	@NotNull
	@Size(min = 1)
	private String medicineName;
	@NotNull
	@Min(value = 1)
	private Float medicineCost;

	@PastOrPresent(message = "provide correct manufacturing date")
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd",shape =Shape.STRING)
	private LocalDate mfd;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd",shape =Shape.STRING)
	private LocalDate expiryDate;
	@NotNull
	@Size(min = 1)
	private String companyName;
	@NotNull
	private CategoryDTO categoryDTO;
	/**
	 * @param srno
	 * @param medicineId
	 * @param medicineName
	 * @param medicineCost
	 * @param mfd
	 * @param expiryDate
	 * @param companyName
	 * @param categoryDTO
	 */
	public MedicineDTO(@NotNull(message = "Please provide medicineId") Integer medicineId,
			String medicineName, Float medicineCost, LocalDate mfd, LocalDate expiryDate, String companyName,
			CategoryDTO categoryDTO) {
		
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
		this.categoryDTO = categoryDTO;
	}
	
	
	
	/** 
	 * @return String
	 */
	public Integer getMedicineId() {
		return medicineId;
	}
	
	/** 
	 * @param medicineId
	 */
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	
	/** 
	 * @return String
	 */
	public String getMedicineName() {
		return medicineName;
	}
	
	/** 
	 * @param medicineName
	 */
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	/** 
	 * @return Float
	 */
	public Float getMedicineCost() {
		return medicineCost;
	}
	
	/** 
	 * @param medicineCost
	 */
	public void setMedicineCost(Float medicineCost) {
		this.medicineCost = medicineCost;
	}
	
	/** 
	 * @return LocalDate
	 */
	public LocalDate getMfd() {
		return mfd;
	}
	
	/** 
	 * @param mfd
	 */
	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
	
	/** 
	 * @return LocalDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	
	/** 
	 * @param expiryDate
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/** 
	 * @return String
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/** 
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/** 
	 * @return CategoryDTO
	 */
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	
	/** 
	 * @param categoryDTO
	 */
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	public MedicineDTO(){
		super();
	}
    
	/** 
	 * @return String
	 */
	@Override
    public String toString() {
        return "MedicineDTO [medicineId=" + medicineId + ", medicineName=" + medicineName
                + ", medicineCost=" + medicineCost + ", mfd=" + mfd + ", expiryDate=" + expiryDate + ", companyName="
                + companyName + ", category=" + categoryDTO + "]";
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicineId == null) ? 0 : medicineId.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineDTO other = (MedicineDTO) obj;
		if (medicineId == null) {
			if (other.medicineId != null)
				return false;
		} else if (!medicineId.equals(other.medicineId))
			return false;
		return true;
	}
	


	

}
