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

/**
 * The Class IOrderServiceImpl.
 */
@Service(value = "orderService")
@Transactional
public class IOrderServiceImpl implements IOrderService {

	/** The order repository. */
	@Autowired
	private IOrderRepository orderRepository;

	/** The customer repository. */
	@Autowired
	private ICustomerRepository customerRepository;

	/**
	 * Convert entity to dto.
	 *
	 * @param order the order
	 * @return the order DTO
	 */
	public OrderDTO convertEntityToDto(Order order) {
		OrderDTO resultOrderDto = new OrderDTO();
		resultOrderDto.setOrderId(order.getOrderId());
		resultOrderDto.setDispatchDate(order.getDispatchDate());
		resultOrderDto.setOrderDate(order.getOrderDate());
		resultOrderDto.setTotalCost(order.getTotalCost());
		resultOrderDto.setOrderStatus(order.getOrderStatus());
		return resultOrderDto;
	}

	/**
	 * Adds the order.
	 *
	 * @param order the order
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
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

	/**
	 * View all order.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<OrderDTO> viewAllOrder() throws InvalidDataException {
		Iterable<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});
		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	/**
	 * View order by id.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public OrderDTO viewOrderById(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	/**
	 * View order by status.
	 *
	 * @param orderStatus the order status
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<OrderDTO> viewOrderByStatus(OrderStatus orderStatus) throws InvalidDataException {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findByOrderStatus(orderStatus);
		List<OrderDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);

		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	/**
	 * Update order.
	 *
	 * @param orderDto the order dto
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException {
		Optional<Order> optional = orderRepository.findById(orderDto.getOrderId());
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));

		if (orderEntity.getOrderDate() != null)
			orderEntity.setOrderDate(orderDto.getOrderDate());
		if (orderEntity.getDispatchDate() != null)
			orderEntity.setDispatchDate(orderDto.getDispatchDate());
		if (orderEntity.getTotalCost() != null)
			orderEntity.setTotalCost(orderDto.getTotalCost());
		if (orderEntity.getOrderStatus() != null)
			orderEntity.setOrderStatus(orderDto.getOrderStatus());

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	/**
	 * Update order status.
	 *
	 * @param orderId     the order id
	 * @param orderStatus the order status
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public OrderDTO updateOrderStatus(Integer orderId, OrderStatus orderStatus) throws InvalidDataException {
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		orderEntity.setOrderStatus(orderStatus);

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	/**
	 * Cancel order.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException {
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		orderEntity.setOrderStatus(OrderStatus.CANCELLED);

		OrderDTO resultOrderDto = convertEntityToDto(orderEntity);
		return resultOrderDto;
	}

	/**
	 * Show all orders by medicine id.
	 *
	 * @param medicineId the medicine id
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	// Need to Test in main
	@Override
	public List<OrderDTO> showAllOrdersByMedicineId(Integer medicineId) throws InvalidDataException {

		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDtos = new ArrayList<>();
		List<Order> orders = new ArrayList<>();

		customers.forEach((customer) -> {
			List<Medicine> medicines = customer.getMedicineList();
			for (Medicine med : medicines) {
				if (Integer.valueOf(med.getMedicineId()).equals(medicineId)) {
					orders.add(customer.getOrder());
				}
			}
		});

		if (orders.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}

		List<OrderDTO> orderDtos = new ArrayList<>();
		for (Order order : orders) {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		}

		return orderDtos;
	}

	/**
	 * Show all orders by customer id.
	 *
	 * @param customerId the customer id
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<OrderDTO> showAllOrdersByCustomerId(Integer customerId) throws InvalidDataException {

		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InvalidDataException("Customer not found"));
		Order order = customer.getOrder();
		OrderDTO resultOrderDto = convertEntityToDto(order);
		List<OrderDTO> orders = new ArrayList<>();
		orders.add(resultOrderDto);
		return orders;
	}

	/**
	 * Show all orders by dispatch date.
	 *
	 * @param date the date
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<OrderDTO> showAllOrdersByDispatchDate(LocalDate date) throws InvalidDataException {
		List<Order> orders = orderRepository.findByDispatchDate(date);
		List<OrderDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	/**
	 * Show all orders by order date.
	 *
	 * @param date the date
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public List<OrderDTO> showAllOrdersByOrderDate(LocalDate date) throws InvalidDataException {
		List<Order> orders = orderRepository.findByOrderDate(date);
		List<OrderDTO> orderDtos = new ArrayList<>();
		orders.forEach((order) -> {
			OrderDTO orderDto = convertEntityToDto(order);
			orderDtos.add(orderDto);
		});

		if (orderDtos.isEmpty()) {
			throw new InvalidDataException("Service.ORDERS_NOT_FOUND");
		}
		return orderDtos;
	}

	/**
	 * Calculate total cost.
	 *
	 * @param orderId the order id
	 * @return the double
	 * @throws InvalidDataException the invalid data exception
	 */
	// Need to Test in main
	@Override
	public Double calculateTotalCost(Integer orderId) throws InvalidDataException {
		// TODO Auto-generated method stub
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isEmpty()) {
			throw new InvalidDataException("Service.ORDER_NOT_FOUND");
		}

		Iterable<Customer> customers = customerRepository.findAll();
		Float totalCost = 0F;
		for (Customer customer : customers) {
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

	/**
	 * Delete order.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	@Override
	public OrderDTO deleteOrder(Integer orderId) throws InvalidDataException {
		Optional<Order> optional = orderRepository.findById(orderId);
		Order orderEntity = optional.orElseThrow(() -> new InvalidDataException("Service.ORDER_NOT_FOUND"));
		OrderDTO orderDto = convertEntityToDto(orderEntity);

		Iterable<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			if (customer.getOrder().getOrderId().equals(orderId)) {
				customer.setOrder(null);
				break;
			}
		}
		orderRepository.delete(orderEntity);

		return orderDto;
	}

}
