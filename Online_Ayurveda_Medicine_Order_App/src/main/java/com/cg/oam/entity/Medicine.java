package com.cg.oam.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer srno;
	private String medicineId;
	private String medicineName;
	private Float medicineCost;
	private LocalDate mfd;
	private LocalDate expiryDate;
	private String companyName;
	//need to add one to one relation here
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId", unique = true)
	private Category category;

	public Medicine(){
		super();
	}
	/**
	 * @param srno
	 * @param medicineId
	 * @param medicineName
	 * @param medicineCost
	 * @param mfd
	 * @param expiryDate
	 * @param companyName
	 * @param category
	 */
	public Medicine(Integer srno,String medicineId, String medicineName, Float medicineCost, LocalDate mfd, LocalDate expiryDate,
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
	 * @return Category
	 */
	public Category getCategory() {
		return category;
	}
	
	/** 
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Medicine [srno=" + srno + ", medicineId=" + medicineId + ", medicineName=" + medicineName
				+ ", medicineCost=" + medicineCost + ", mfd=" + mfd + ", expiryDate=" + expiryDate + ", companyName="
				+ companyName + ", category=" + category + "]";
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
		Medicine other = (Medicine) obj;
		if (srno == null) {
			if (other.srno != null)
				return false;
		} else if (!srno.equals(other.srno))
			return false;
		return true;
	}

	

}
