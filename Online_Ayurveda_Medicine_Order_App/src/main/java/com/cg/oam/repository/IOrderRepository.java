package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;

public interface IOrderRepository {
	
	public OrderDTO addOrder(OrderDTO order);
	
	public OrderDTO viewOrder(OrderDTO order);
	
	public OrderDTO updateOrder(OrderDTO order);
	
	public OrderDTO cancelOrder(OrderDTO order);
	
	public List<OrderDTO> showAllOrders(int medicineId);
	
	public List<OrderDTO> showAllOrders(CustomerDTO customer);
	
	public List<OrderDTO> showAllOrders(LocalDate date);
	
	public double calculateTotalCost(int orderId);

}
