package com.cg.oam.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

/**
 * The Class OrderDTO.
 */
public class OrderDTO {
	
	/** The order id. */
	@Min(value =1,message = "Order Id should not be less than one")
	private Integer orderId;
	
	/** The order date. */
	@PastOrPresent(message = "Order date should be past or present to current date")
	private LocalDate orderDate;
	
	/** The dispatch date. */
	private LocalDate dispatchDate;
	
	/** The total cost. */
	@Min(value = 0,message = "Total cost should not be less than zero")
	private Float totalCost;
	
	/** The order status. */
	private OrderStatus orderStatus;
	
	/**
	 * Instantiates a new order DTO.
	 */
	public OrderDTO() {
		super();
	}
	
	/**
	 * Instantiates a new order DTO.
	 *
	 * @param orderId the order id
	 * @param orderDate the order date
	 * @param dispatchDate the dispatch date
	 * @param totalCost the total cost
	 * @param orderStatus the order status
	 */
	public OrderDTO(Integer orderId, LocalDate orderDate, LocalDate dispatchDate, Float totalCost,OrderStatus orderStatus) {
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", orderDate=" + orderDate + ", dispatchDate=" + dispatchDate
				+ ", totalCost=" + totalCost + ", orderStatus=" + orderStatus + "]";
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(orderId);
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
		OrderDTO other = (OrderDTO) obj;
		return Objects.equals(orderId, other.orderId);
	}
	

}
