package com.cg.oam.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.OrderDetail;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IOrderDetailRepository;

@Service(value = "orderDetailService")
@Transactional
public class IOrderDetailServiceImpl implements IOrderDetailService{

	@Autowired
	private IOrderDetailRepository orderDetailRepository;
	@Autowired
	private ICustomerRepository customerRepository;
	
	
	public OrderDetailDTO convertEntityToDto(OrderDetail order) {
		OrderDetailDTO resultOrderDto = new OrderDetailDTO();
		resultOrderDto.setOrderDetailId(order.getOrderDetailId());
		resultOrderDto.setDispatchDate(order.getDispatchDate());
		resultOrderDto.setOrderDate(order.getOrderDate());
		resultOrderDto.setTotalCost(order.getTotalCost());
		resultOrderDto.setOrderStatus(order.getOrderStatus());
		Customer c= order.getCustomer();
		if(c!=null) {
			CustomerDTO cd =new CustomerDTO();
			cd.setCustomerId(c.getCustomerId());
			cd.setCustomerName(c.getCustomerName());
			cd.setCustomerPassword(c.getCustomerPassword());
			resultOrderDto.setCustomer(cd);
		}else {
			resultOrderDto.setCustomer(null);
		}
		
		
		return resultOrderDto;
	}
	
	@Override
	public List<OrderDetailDTO> viewAllOrderDetail() throws InvalidDataException {
		Iterable<OrderDetail> orders = orderDetailRepository.findAll();
		List<OrderDetailDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDetailDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});
		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public OrderDetailDTO viewOrderDetailById(Integer orderDetailId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
		OrderDetail orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));

		OrderDetailDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public List<OrderDetailDTO> viewOrderDetailByStatus(OrderStatus orderStatus) throws InvalidDataException {
		// TODO Auto-generated method stub
		List<OrderDetail> orders = orderDetailRepository.findByOrderStatus(orderStatus);
		List<OrderDetailDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDetailDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);

		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public OrderDetailDTO addOrderDetail(OrderDetailDTO order) throws InvalidDataException {
		
		if(order.getOrderDetailId()!=null) {
			Optional<OrderDetail> optional = orderDetailRepository.findById(order.getOrderDetailId());
			if(optional.isPresent()) {
				throw new InvalidDataException("Service.ORDER_FOUND");
			}
		}
		OrderDetail orderEntity = new OrderDetail();
		orderEntity.setOrderDate(order.getOrderDate());
		orderEntity.setDispatchDate(order.getDispatchDate());
		orderEntity.setTotalCost(order.getTotalCost());
		orderEntity.setOrderStatus(OrderStatus.CREATED);
		Optional<Customer> optionalCust = customerRepository.findById(order.getCustomer().getCustomerId());
		Customer cust = optionalCust.get();
		orderEntity.setCustomer(cust);
		OrderDetail createdOrder = orderDetailRepository.save(orderEntity);

		OrderDetailDTO createdOrderDto = convertEntityToDto(createdOrder);
		return createdOrderDto;
	}

	@Override
	public OrderDetailDTO updateOrderDetail(OrderDetailDTO orderDetailDto) throws InvalidDataException {
		Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailDto.getOrderDetailId());
		OrderDetail orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));

		if (orderEntity.getOrderDate() != null)
			orderEntity.setOrderDate(orderDetailDto.getOrderDate());
		if (orderEntity.getDispatchDate() != null)
			orderEntity.setDispatchDate(orderDetailDto.getDispatchDate());
		if (orderEntity.getTotalCost() != null)
			orderEntity.setTotalCost(orderDetailDto.getTotalCost());
		if (orderEntity.getOrderStatus() != null)
			orderEntity.setOrderStatus(orderDetailDto.getOrderStatus());
		if (orderEntity.getCustomer() != null) {
			Optional<Customer> optionalCust = customerRepository.findById(orderDetailDto.getCustomer().getCustomerId());
			Customer cust = optionalCust.orElseThrow(() -> new InvalidDataException("Service.CUSTOMERS_NOT_FOUND"));
			orderEntity.setCustomer(cust);
		}
		OrderDetailDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDetailDTO updateOrderDetailStatus(Integer orderDetailId, OrderStatus orderStatus)
			throws InvalidDataException {
		Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
		OrderDetail orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		orderEntity.setOrderStatus(orderStatus);

		OrderDetailDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public OrderDetailDTO deleteOrderDetail(Integer orderDetailId) throws InvalidDataException {
		Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
		OrderDetail orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		orderEntity.setCustomer(null);
		OrderDetailDTO orderDto = convertEntityToDto(orderEntity);
		
		orderDetailRepository.delete(orderEntity);

		return orderDto;
	}

	@Override
	public OrderDetailDTO cancelOrderDetail(Integer orderDetailId) throws InvalidDataException {
		Optional<OrderDetail> optional = orderDetailRepository.findById(orderDetailId);
		OrderDetail orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		orderEntity.setOrderStatus(OrderStatus.CANCELLED);

		OrderDetailDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	@Override
	public List<OrderDetailDTO> showAllOrderDetailssByCustomerId(Integer customerId) throws InvalidDataException {
		Optional<Customer> optionalCust = customerRepository.findById(customerId);
		Customer cust = optionalCust.orElseThrow(() -> new InvalidDataException("Service.CUSTOMERS_NOT_FOUND"));
		
		List<OrderDetail> orders = orderDetailRepository.findByCustomer(cust);
		List<OrderDetailDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDetailDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public List<OrderDetailDTO> showAllOrderDetailsByOrderDate(LocalDate orderDate) throws InvalidDataException {
		List<OrderDetail> orders = orderDetailRepository.findByOrderDate(orderDate);
		List<OrderDetailDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDetailDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	@Override
	public List<OrderDetailDTO> showAllOrderDetailsByDispatchDate(LocalDate dispatchDate) throws InvalidDataException {
		List<OrderDetail> orders = orderDetailRepository.findByDispatchDate(dispatchDate);
		List<OrderDetailDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDetailDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

}
