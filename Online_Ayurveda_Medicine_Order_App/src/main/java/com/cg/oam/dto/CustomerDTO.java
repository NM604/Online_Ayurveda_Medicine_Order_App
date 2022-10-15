package com.cg.oam.dto;

import java.util.List;

import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;

public class CustomerDTO {
	
	private int customerId;
	private String customerName;
	private String customerPassword;
	private List<Medicine> medicineList;
	private List<Order> order;
	public CustomerDTO() {
		
	}
	public CustomerDTO(int customerId, String customerName, String customerPassword, List<Medicine> medicineList,
			List<Order> order) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.medicineList = medicineList;
		this.order = order;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public List<Medicine> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}
	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	

}
