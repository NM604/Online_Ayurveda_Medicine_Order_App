package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.dto.OrderStatus;
import com.cg.oam.entity.Customer;
import com.cg.oam.entity.OrderDetail;

public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrderStatus(OrderStatus orderStatus);
	List<OrderDetail> findByOrderDate(LocalDate orderDate);
	List<OrderDetail> findByDispatchDate(LocalDate dispatchDate);
	List<OrderDetail> findByCustomer(Customer customer);
}
