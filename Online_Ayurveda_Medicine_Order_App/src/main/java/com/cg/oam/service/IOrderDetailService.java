package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.exception.InvalidDataException;

public interface IOrderDetailService {

	public List<OrderDetailDTO> viewAllOrderDetail() throws InvalidDataException;

	public OrderDetailDTO viewOrderDetailById(Integer orderDetailId) throws InvalidDataException;

	public List<OrderDetailDTO> viewOrderDetailByStatus(OrderStatus orderStatus) throws InvalidDataException;

	public OrderDetailDTO addOrderDetail(OrderDetailDTO order) throws InvalidDataException;

	public OrderDetailDTO updateOrderDetail(OrderDetailDTO orderDetailDto) throws InvalidDataException;

	public OrderDetailDTO updateOrderDetailStatus(Integer orderDetailId, OrderStatus orderStatus)
			throws InvalidDataException;

	public OrderDetailDTO deleteOrderDetail(Integer orderDetailId) throws InvalidDataException;

	public OrderDetailDTO cancelOrderDetail(Integer orderDetailId) throws InvalidDataException;

	public List<OrderDetailDTO> showAllOrderDetailssByCustomerId(Integer customerId) throws InvalidDataException;

	public List<OrderDetailDTO> showAllOrderDetailsByOrderDate(LocalDate orderDate) throws InvalidDataException;

	public List<OrderDetailDTO> showAllOrderDetailsByDispatchDate(LocalDate dispatchDate) throws InvalidDataException;


}
