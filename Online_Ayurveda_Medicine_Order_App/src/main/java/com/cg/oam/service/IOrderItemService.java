package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.OrderItemDTO;
import com.cg.oam.exception.InvalidDataException;

public interface IOrderItemService {
	public OrderItemDTO addOrderItem(OrderItemDTO orderItem) throws InvalidDataException;

	public List<OrderItemDTO> viewAllOrderItems() throws InvalidDataException;

	public OrderItemDTO viewOrderItemById(Integer orderItemId) throws InvalidDataException;
	

	public OrderItemDTO updateOrderItem(OrderItemDTO orderItem) throws InvalidDataException;

	public List<OrderItemDTO> showAllOrderItemsByMedicineId(Integer medicineId) throws InvalidDataException;
	
	public List<OrderItemDTO> showAllOrderItemsByOrderDetailId(Integer orderDetailId) throws InvalidDataException;

	public OrderItemDTO deleteOrderItem(Integer orderItemId) throws InvalidDataException;

}
