package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Order;

/**
 * The Interface IOrderRepository.
 */
public interface IOrderRepository extends CrudRepository<Order, Integer> {
	
	/**
	 * Find by order status.
	 *
	 * @param orderStatus the order status
	 * @return the list
	 */
	List<Order> findByOrderStatus(OrderStatus orderStatus);
	
	/**
	 * Find by order date.
	 *
	 * @param orderDate the order date
	 * @return the list
	 */
	List<Order> findByOrderDate(LocalDate orderDate);
	
	/**
	 * Find by dispatch date.
	 *
	 * @param dispatchDate the dispatch date
	 * @return the list
	 */
	List<Order> findByDispatchDate(LocalDate dispatchDate);
}
