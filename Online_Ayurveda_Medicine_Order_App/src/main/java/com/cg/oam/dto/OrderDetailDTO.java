package com.cg.oam.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import com.cg.oam.entity.Customer;

public class OrderDetailDTO {
	@Min(value = 1, message = "Order Id should not be less than one")
	private Integer orderDetailId;
	@PastOrPresent(message = "Order date should be past or present to current date")
	private LocalDate orderDate;
	private LocalDate dispatchDate;
	private OrderStatus orderStatus;
	@Min(value = 0, message = "Total cost should not be less than zero")
	private Integer totalCost;
	private CustomerDTO customer;

	public OrderDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailDTO(Integer orderDetailId, LocalDate orderDate, LocalDate dispatchDate, OrderStatus orderStatus,
			Integer totalCost, CustomerDTO customer) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.orderStatus = orderStatus;
		this.totalCost = totalCost;
		this.customer = customer;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderDetailId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailDTO other = (OrderDetailDTO) obj;
		return Objects.equals(orderDetailId, other.orderDetailId);
	}

	@Override
	public String toString() {
		return "OrderDetailDTO [orderDetailId=" + orderDetailId + ", orderDate=" + orderDate + ", dispatchDate="
				+ dispatchDate + ", orderStatus=" + orderStatus + ", totalCost=" + totalCost + ", customer=" + customer
				+ "]";
	}
	

}
