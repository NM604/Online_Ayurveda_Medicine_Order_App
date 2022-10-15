package com.cg.oam.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
// Entity annotstion checked
@Entity
public class Medicine {
	// id annotation checked
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	// public Medicine(String medicineId, String medicineName, Float medicineCost, LocalDate mfd, LocalDate expiryDate,
	// 		String companyName, Category category) {
	// 	super();
	// 	this.medicineId = medicineId;
	// 	this.medicineName = medicineName;
	// 	this.medicineCost = medicineCost;
	// 	this.mfd = mfd;
	// 	this.expiryDate = expiryDate;
	// 	this.companyName = companyName;
	// 	this.category = category;
	// }
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

}
