package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;

public interface IOrderService {
	public OrderDTO addOrder(OrderDTO order) throws InvalidDataException;
	public OrderDTO viewOrder(OrderDTO orderDto) throws InvalidDataException;
	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException;
	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException;
	public List<OrderDTO> showAllOrders(Integer mediceId) throws InvalidDataException;
	public List<OrderDTO> showAllOrders(CustomerDTO customerDTO) throws InvalidDataException;
	public List<OrderDTO> showAllOrders(LocalDate date) throws InvalidDataException;
	public Double calculateTotalCost(Integer orderId) throws InvalidDataException;
}
