package com.cg.oam.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class MedicineDTO {

	private Integer srno;

	@NotNull(message = "Please provide medicineId")
	private String medicineId;

	private String medicineName;
	private Float medicineCost;
	@PastOrPresent(message = "provide correct manufacturing date")
	private LocalDate mfd;
	@FutureOrPresent(message = "provide correct expiry date")
	private LocalDate expiryDate;
	private String companyName;
	// private Category category;
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
	public MedicineDTO(Integer srno, @NotNull(message = "Please provide medicineId") String medicineId,
			String medicineName, Float medicineCost, LocalDate mfd, LocalDate expiryDate, String companyName,
			CategoryDTO categoryDTO) {
		this.srno = srno;
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
		this.categoryDTO = categoryDTO;
	}
	
	/** 
	 * @return Integer
	 */
	public Integer getSrno() {
		return srno;
	}
	
	/** 
	 * @param srno
	 */
	public void setSrno(Integer srno) {
		this.srno = srno;
	}
	
	/** 
	 * @return String
	 */
	public String getMedicineId() {
		return medicineId;
	}
	
	/** 
	 * @param medicineId
	 */
	public void setMedicineId(String medicineId) {
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
        return "MedicineDTO [srno=" + srno + ", medicineId=" + medicineId + ", medicineName=" + medicineName
                + ", medicineCost=" + medicineCost + ", mfd=" + mfd + ", expiryDate=" + expiryDate + ", companyName="
                + companyName + ", category=" + categoryDTO + "]";
    }
	
	/** 
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((srno == null) ? 0 : srno.hashCode());
		return result;
	}
	
	/** 
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineDTO other = (MedicineDTO) obj;
		if (srno == null) {
			if (other.srno != null)
				return false;
		} else if (!srno.equals(other.srno))
			return false;
		return true;
	}

	
	

}
