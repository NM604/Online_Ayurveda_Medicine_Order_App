package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;

public class IOrderRepositoryImpl implements IOrderRepository{

	@Override
	public OrderDTO addOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO viewOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO updateOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO cancelOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> showAllOrders(int medicineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> showAllOrders(CustomerDTO customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> showAllOrders(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateTotalCost(int orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
