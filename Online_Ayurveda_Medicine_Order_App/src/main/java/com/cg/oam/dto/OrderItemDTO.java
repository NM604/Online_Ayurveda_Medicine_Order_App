package com.cg.oam.dto;

import java.util.Objects;

import javax.validation.constraints.Min;

public class OrderItemDTO {

	@Min(value = 1, message = "OrderItem Id should not be less than one")
	private Integer orderItemId;

	@Min(value = 1, message = "Quantity should be greater than zero")
	private Integer quantity;

	@Min(value = 1, message = "Price should be greater than zero")
	private Integer price;

	private OrderDetailDTO orderDetail;
	private MedicineDTO medicine;

	public OrderItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItemDTO(Integer orderItemId, Integer quantity, Integer price, OrderDetailDTO orderDetail,
			MedicineDTO medicine) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.price = price;
		this.orderDetail = orderDetail;
		this.medicine = medicine;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public OrderDetailDTO getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetailDTO orderDetail) {
		this.orderDetail = orderDetail;
	}

	public MedicineDTO getMedicine() {
		return medicine;
	}

	public void setMedicine(MedicineDTO medicine) {
		this.medicine = medicine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemDTO other = (OrderItemDTO) obj;
		return Objects.equals(orderItemId, other.orderItemId);
	}
	
	

}
