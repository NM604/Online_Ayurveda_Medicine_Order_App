package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderRepository;

@Service(value ="orderService")
@Transactional
public class IOrderServiceImpl implements IOrderService{

	@Autowired
	private IOrderRepository orderRepository;
	
	public OrderDTO convertEntityToDto(Order order) {
		OrderDTO resultOrderDto = new OrderDTO();
		resultOrderDto.setOrderId(order.getOrderId());
		resultOrderDto.setDispatchDate(order.getDispatchDate());
		resultOrderDto.setOrderDate(order.getOrderDate());
		resultOrderDto.setTotalCost(order.getTotalCost());
		resultOrderDto.setOrderStatus(order.getOrderStatus());
		return resultOrderDto;
	}
	
	@Override
	public OrderDTO addOrder(OrderDTO order) throws InvalidDataException {
		Order orderEntity = new Order();
		orderEntity.setOrderDate(order.getOrderDate());
		orderEntity.setDispatchDate(order.getDispatchDate());
		orderEntity.setTotalCost(order.getTotalCost());
		orderEntity.setOrderStatus(OrderStatus.CREATED);
		Order createdOrder = orderRepository.save(orderEntity);
		
		OrderDTO createdOrderDto = convertEntityToDto(createdOrder);
		return createdOrderDto;
	}

	@Override
	public OrderDTO viewOrder(OrderDTO orderDto) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderDto.getOrderId());
		Order orderEntity = optional.orElseThrow(()->new InvalidDataException("Order not found"));
		
		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderDto.getOrderId());
		Order orderEntity = optional.orElseThrow(()->new InvalidDataException("Order not found"));
		
		//OrderDTO resultOrderDto = new OrderDTO();
		if(orderEntity.getOrderDate()!=null)
			orderEntity.setOrderDate(orderEntity.getOrderDate());
		if(orderEntity.getDispatchDate()!=null)
			orderEntity.setDispatchDate(orderEntity.getDispatchDate());
		if(orderEntity.getTotalCost()!=null)
			orderEntity.setTotalCost(orderEntity.getTotalCost());
		
		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(()->new InvalidDataException("Order not found"));
		orderEntity.setOrderStatus(OrderStatus.CANCELLED);
		
		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public List<OrderDTO> showAllOrders(Integer mediceId) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> showAllOrders(CustomerDTO customerDTO) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDTO> showAllOrders(LocalDate date) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateTotalCost(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
