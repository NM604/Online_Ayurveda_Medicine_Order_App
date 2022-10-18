package com.cg.oam.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;


/**
 * The Class CustomerDTO.
 */
public class CustomerDTO {
	
	/** The customer id. */
	@Min(value =1,message = "Order Id should not be less than one")
	private int customerId;
	
	/** The customer name. */
	@NotNull(message = "Please provide name")
	private String customerName;
	
	/** The customer password. */
	@NotNull(message = "Please provide password")
	private String customerPassword;
	
	/** The medicine list. */
	private List<Medicine> medicineList;
	
	/** The order. */
	private Order order;
	
	/**
	 * Instantiates a new customer DTO.
	 */
	public CustomerDTO() {
		
	}
	
	/**
	 * Instantiates a new customer DTO.
	 *
	 * @param customerId the customer id
	 * @param customerName the customer name
	 * @param customerPassword the customer password
	 * @param medicineList the medicine list
	 * @param order the order
	 */
	public CustomerDTO(int customerId, String customerName, String customerPassword, List<Medicine> medicineList,
			Order order) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.medicineList = medicineList;
		this.order = order;
	}
	
	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * Gets the customer password.
	 *
	 * @return the customer password
	 */
	public String getCustomerPassword() {
		return customerPassword;
	}
	
	/**
	 * Sets the customer password.
	 *
	 * @param customerPassword the new customer password
	 */
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	/**
	 * Gets the medicine list.
	 *
	 * @return the medicine list
	 */
	public List<Medicine> getMedicineList() {
		return medicineList;
	}
	
	/**
	 * Sets the medicine list.
	 *
	 * @param medicineList the new medicine list
	 */
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}
	
	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
