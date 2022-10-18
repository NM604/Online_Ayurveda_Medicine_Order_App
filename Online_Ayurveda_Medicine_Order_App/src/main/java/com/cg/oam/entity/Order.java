package com.cg.oam.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cg.oam.dto.OrderStatus;

/**
 * The Class Order.
 */
@Entity
@Table(name = "orders")
public class Order {
	
	/** The order id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	/** The order date. */
	private LocalDate orderDate;
	
	/** The dispatch date. */
	private LocalDate dispatchDate;
	
	/** The total cost. */
	private Float totalCost;
	
	/** The order status. */
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;
	
	/**
	 * Instantiates a new order.
	 */
	public Order() {
		super();
	}
	
	/**
	 * Instantiates a new order.
	 *
	 * @param orderId the order id
	 * @param orderDate the order date
	 * @param dispatchDate the dispatch date
	 * @param totalCost the total cost
	 * @param orderStatus the order status
	 */
	public Order(Integer orderId, LocalDate orderDate, LocalDate dispatchDate, Float totalCost,
			OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
	}


	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public Integer getOrderId() {
		return orderId;
	}
	
	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	/**
	 * Sets the order date.
	 *
	 * @param orderDate the new order date
	 */
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * Gets the dispatch date.
	 *
	 * @return the dispatch date
	 */
	public LocalDate getDispatchDate() {
		return dispatchDate;
	}
	
	/**
	 * Sets the dispatch date.
	 *
	 * @param dispatchDate the new dispatch date
	 */
	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
	/**
	 * Gets the total cost.
	 *
	 * @return the total cost
	 */
	public Float getTotalCost() {
		return totalCost;
	}
	
	/**
	 * Sets the total cost.
	 *
	 * @param totalCost the new total cost
	 */
	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}
	
	/**
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * Sets the order status.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getOrderId() == null) ? 0 : this.getOrderId().hashCode());
		return result;
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
		Order other = (Order) obj;
		if (this.getOrderId() == null) {
			if (other.getOrderId() != null)
				return false;
		} 
		else if (!this.getOrderId().equals(other.getOrderId()))
			return false;
		return true;
	}
}
