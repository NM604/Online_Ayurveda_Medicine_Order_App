package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cg.oam.dto.CustomerDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.entity.Order;

public interface IOrderRepository extends CrudRepository<Order, Integer>{
	@Query("SELECT o FROM Order o WHERE o.orderDate = :ordDate")
	List<Order> findAllOrdersByOrderDate(@Param("ordDate") LocalDate ordDate); 
	
	@Query("SELECT o FROM Order o WHERE o.dispatchDate = :dispatchDate")
	List<Order> findAllOrdersByDispatchDate(@Param("dispatchDate") LocalDate dispatchDate); 
	
	@Query("SELECT c.orderId FROM Customer c WHERE c.customerId = :custId")
	List<Integer> findAllOrdersByCustomerId(@Param("custId") Integer custId); 
}
