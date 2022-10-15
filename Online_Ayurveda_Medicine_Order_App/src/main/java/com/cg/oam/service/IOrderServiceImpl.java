package com.cg.oam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderRepository;

@Service(value ="orderService")
@Transactional
public class IOrderServiceImpl implements IOrderService{

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private ICustomerRepository customerRepository;

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
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Order not found"));

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDTO viewOrderByOrderId(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Order not found"));

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderDto.getOrderId());
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Order not found"));

		// OrderDTO resultOrderDto = new OrderDTO();
		if (orderEntity.getOrderDate() != null)
			orderEntity.setOrderDate(orderEntity.getOrderDate());
		if (orderEntity.getDispatchDate() != null)
			orderEntity.setDispatchDate(orderEntity.getDispatchDate());
		if (orderEntity.getTotalCost() != null)
			orderEntity.setTotalCost(orderEntity.getTotalCost());

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Order not found"));
		orderEntity.setOrderStatus(OrderStatus.CANCELLED);

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public List<OrderDTO> showAllOrders(Integer medicineId) throws InvalidDataException {
		Iterable<Customer> customers = customerRepository.findAll();

		List<CustomerDTO> customerDtos = new ArrayList<>();
		List<Order> orders = new ArrayList<>();

		customers.forEach((customer) -> {
			List<Medicine> medicines = customer.getMedicineList();
			for (Medicine med : medicines) {
				if (med.getMedicineId().equals(medicineId)) {
					orders.add(customer.getOrder());
				}
			}
		});

		if (orders.isEmpty()) {
			throw new InvalidDataException("Orders not found");
		}

		List<OrderDTO> orderDtos = new ArrayList<>();
		for (Order order : orders) {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		}

		return orderDtos;
	}

	@Override
	public List<OrderDTO> showAllOrders(CustomerDTO customerDTO) throws InvalidDataException {
		// TODO Auto-generated method stub
		List<Integer> orderIds = orderRepository.findAllOrdersByCustomerId(customerDTO.getCustomerId());

		List<OrderDTO> orderDtos = new ArrayList<>();
		for (Integer id : orderIds) {
			OrderDTO orderDto = viewOrderByOrderId(id);
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

	@Override
	public List<OrderDTO> showAllOrdersByDispatchDate(LocalDate date) throws InvalidDataException {
		List<Order> orders = orderRepository.findAllOrdersByDispatchDate(date);
		List<OrderDTO> orderDtos = new ArrayList<>();
		if (orders.isEmpty() || orders == null) {
			throw new InvalidDataException("Orders Not found");
		}

		orders.forEach((orderEntity) -> {
			OrderDTO orderDto = convertEntityToDto(orderEntity);
			orderDtos.add(orderDto);
		});

		return orderDtos;
	}

	@Override
	public List<OrderDTO> showAllOrdersByOrderDate(LocalDate date) throws InvalidDataException {
		List<Order> orders = orderRepository.findAllOrdersByOrderDate(date);
		List<OrderDTO> orderDtos = new ArrayList<>();
		if (orders.isEmpty() || orders == null) {
			throw new InvalidDataException("Orders Not found");
		}

		orders.forEach((orderEntity) -> {
			OrderDTO orderDto = convertEntityToDto(orderEntity);
			orderDtos.add(orderDto);
		});

		return orderDtos;
	}

	@Override
	public Double calculateTotalCost(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Iterable<Customer> customers = customerRepository.findAll();

		List<CustomerDTO> customerDtos = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		Float totalCost = 0F;
		for(Customer customer:customers) {
			if (customer.getOrder().getOrderId().equals(orderId)) {
				List<Medicine> medicines = customer.getMedicineList();
				
				for (Medicine med : medicines) {
					totalCost += med.getMedicineCost();
				}
				customer.getOrder().setTotalCost(totalCost);
				break;
			}
		}
		return totalCost.doubleValue();
	}
	
}
