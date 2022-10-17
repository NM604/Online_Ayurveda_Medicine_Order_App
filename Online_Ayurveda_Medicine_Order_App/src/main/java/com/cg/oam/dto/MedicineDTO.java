package com.cg.oam.dto;

import java.time.LocalDate;

import com.cg.oam.entity.Category;

public class MedicineDTO {

	private Integer srno;

	private String medicineId;
	private String medicineName;
	private Float medicineCost;
	private LocalDate mfd;
	private LocalDate expiryDate;
	private String companyName;
	private Category category;
	public MedicineDTO(){
		super();
	}
	public MedicineDTO(Integer srno,String medicineId, String medicineName, Float medicineCost, LocalDate mfd, LocalDate expiryDate,
			String companyName, Category category) {
		super();
		this.srno = srno;
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCost = medicineCost;
		this.mfd = mfd;
		this.expiryDate = expiryDate;
		this.companyName = companyName;
		this.category = category;
	}
	public Integer getSrno() {
		return srno;
	}
	public void setSrno(Integer srno) {
		this.srno = srno;
	}
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Float getMedicineCost() {
		return medicineCost;
	}
	public void setMedicineCost(Float medicineCost) {
		this.medicineCost = medicineCost;
	}
	public LocalDate getMfd() {
		return mfd;
	}
	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
    @Override
    public String toString() {
        return "MedicineDTO [srno=" + srno + ", medicineId=" + medicineId + ", medicineName=" + medicineName
                + ", medicineCost=" + medicineCost + ", mfd=" + mfd + ", expiryDate=" + expiryDate + ", companyName="
                + companyName + ", category=" + category + "]";
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((srno == null) ? 0 : srno.hashCode());
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
		if (srno == null) {
			if (other.srno != null)
				return false;
		} else if (!srno.equals(other.srno))
			return false;
		return true;
	}

	
	

}
