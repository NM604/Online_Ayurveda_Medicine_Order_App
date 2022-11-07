package com.cg.oam.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



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
	public CustomerDTO(int customerId, String customerName, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
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
	
	
	
	

}
