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
	
	/** The medicine list. */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cust_Id")
	private List<Medicine> medicineList;
	
	/** The order. */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_Id")
	private Order order;
	
	/**
	 * Instantiates a new customer.
	 */
	public Customer() {
	}
	
	/**
	 * Instantiates a new customer.
	 *
	 * @param customerId the customer id
	 * @param customerName the customer name
	 * @param customerPassword the customer password
	 * @param medicineList the medicine list
	 * @param order the order
	 */
	public Customer(Integer customerId, String customerName, String customerPassword, List<Medicine> medicineList,
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
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(customerId, customerName, customerPassword, medicineList, order);
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(customerName, other.customerName)
				&& Objects.equals(customerPassword, other.customerPassword)
				&& Objects.equals(medicineList, other.medicineList) && Objects.equals(order, other.order);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", medicineList=" + medicineList + ", order=" + order + "]";
	}
	
	

}

