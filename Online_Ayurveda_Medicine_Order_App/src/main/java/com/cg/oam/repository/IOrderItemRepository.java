package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cg.oam.entity.Medicine;
import com.cg.oam.entity.OrderDetail;
import com.cg.oam.entity.OrderItem;

public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer>{
	List<OrderItem> findByMedicine(Medicine medicine);
	List<OrderItem> findByOrderDetail(OrderDetail orderDetail);
}
