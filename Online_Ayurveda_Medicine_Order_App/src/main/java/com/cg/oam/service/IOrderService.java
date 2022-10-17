package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;

public interface IOrderService {
	public OrderDTO addOrder(OrderDTO order) throws InvalidDataException;

	public List<OrderDTO> viewAllOrder() throws InvalidDataException;

	public OrderDTO viewOrderById(Integer orderId) throws InvalidDataException;

	public List<OrderDTO> viewOrderByStatus(OrderStatus orderStatus) throws InvalidDataException;

	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException;

	public OrderDTO updateOrderStatus(Integer OrderId, OrderStatus orderStatus) throws InvalidDataException;

	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException;

	public List<OrderDTO> showAllOrdersByMedicine(Integer medicineId) throws InvalidDataException;

	public List<OrderDTO> showAllOrdersByCustomer(Integer customerId) throws InvalidDataException;

	public List<OrderDTO> showAllOrdersByOrderDate(LocalDate date) throws InvalidDataException;

	public List<OrderDTO> showAllOrdersByDispatchDate(LocalDate date) throws InvalidDataException;

	public Double calculateTotalCost(Integer orderId) throws InvalidDataException;

	public OrderDTO deleteOrder(Integer orderId) throws InvalidDataException;

}
