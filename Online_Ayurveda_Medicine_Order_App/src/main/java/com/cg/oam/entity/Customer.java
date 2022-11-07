package com.cg.oam.entity;

import java.util.List;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



/**
 * The Class Customer.
 */
@Entity
public class Customer {
	
	/** The customer id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	/** The customer name. */
	private String customerName;
	
	/** The customer password. */
	private String customerPassword;
	
	
	
	/**
	 * Instantiates a new customer.
	 */
	public Customer() {
	}



	public Customer(Integer customerId, String customerName, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
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



	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerId, other.customerId);
	}
	
	
	
	

}

