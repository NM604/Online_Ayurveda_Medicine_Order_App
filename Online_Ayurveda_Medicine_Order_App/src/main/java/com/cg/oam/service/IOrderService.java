package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Order;
import com.cg.oam.exception.InvalidDataException;

/**
 * The Interface IOrderService.
 */
public interface IOrderService {
	
	/**
	 * Adds the order.
	 *
	 * @param order the order
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO addOrder(OrderDTO order) throws InvalidDataException;

	/**
	 * View all order.
	 *
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> viewAllOrder() throws InvalidDataException;

	/**
	 * View order by id.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO viewOrderById(Integer orderId) throws InvalidDataException;

	/**
	 * View order by status.
	 *
	 * @param orderStatus the order status
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> viewOrderByStatus(OrderStatus orderStatus) throws InvalidDataException;

	/**
	 * Update order.
	 *
	 * @param orderDto the order dto
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO updateOrder(OrderDTO orderDto) throws InvalidDataException;

	/**
	 * Update order status.
	 *
	 * @param OrderId the order id
	 * @param orderStatus the order status
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO updateOrderStatus(Integer OrderId, OrderStatus orderStatus) throws InvalidDataException;

	/**
	 * Cancel order.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO cancelOrder(Integer orderId) throws InvalidDataException;

	/**
	 * Show all orders by medicine id.
	 *
	 * @param medicineId the medicine id
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> showAllOrdersByMedicineId(Integer medicineId) throws InvalidDataException;

	/**
	 * Show all orders by customer id.
	 *
	 * @param customerId the customer id
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> showAllOrdersByCustomerId(Integer customerId) throws InvalidDataException;

	/**
	 * Show all orders by order date.
	 *
	 * @param date the date
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> showAllOrdersByOrderDate(LocalDate date) throws InvalidDataException;

	/**
	 * Show all orders by dispatch date.
	 *
	 * @param date the date
	 * @return the list
	 * @throws InvalidDataException the invalid data exception
	 */
	public List<OrderDTO> showAllOrdersByDispatchDate(LocalDate date) throws InvalidDataException;

	/**
	 * Calculate total cost.
	 *
	 * @param orderId the order id
	 * @return the double
	 * @throws InvalidDataException the invalid data exception
	 */
	public Double calculateTotalCost(Integer orderId) throws InvalidDataException;

	/**
	 * Delete order.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws InvalidDataException the invalid data exception
	 */
	public OrderDTO deleteOrder(Integer orderId) throws InvalidDataException;

}
