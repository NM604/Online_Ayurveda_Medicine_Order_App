package com.cg.oam.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderItemId;
	private Integer quantity;
	private Integer price;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_detail_id")
	private OrderDetail orderDetail;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="med_id")
	private Medicine medicine;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Integer orderItemId, Integer quantity, Integer price, OrderDetail orderDetail, Medicine medicine) {
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

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(orderItemId, other.orderItemId);
	}
	
}
