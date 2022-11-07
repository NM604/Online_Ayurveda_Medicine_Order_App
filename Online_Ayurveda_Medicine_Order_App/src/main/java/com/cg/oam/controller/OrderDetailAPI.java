package com.cg.oam.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IOrderDetailService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderStatus;

@RestController
@RequestMapping(value = "/oam")
@Validated
public class OrderDetailAPI {
	@Autowired
	IOrderDetailService orderService;

	/** The environment. */
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/order-details")
	public ResponseEntity<List<OrderDetailDTO>> viewAllOrderDetails() throws InvalidDataException {
		List<OrderDetailDTO> orders = orderService.viewAllOrderDetail();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order-details/{orderId}")
	public ResponseEntity<OrderDetailDTO> viewOrderDetailById(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDetailDTO orders = orderService.viewOrderDetailById(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order-details/orderStatus/{orderStatus}")
	public ResponseEntity<List<OrderDetailDTO>> viewOrderDetailByStatus(@PathVariable OrderStatus orderStatus)
			throws InvalidDataException {
		List<OrderDetailDTO> orderList = orderService.viewOrderDetailByStatus(orderStatus);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/order-details")
	public ResponseEntity<String> addOrderDetail(@Valid @RequestBody OrderDetailDTO order) throws InvalidDataException {
		OrderDetailDTO orderDto = orderService.addOrderDetail(order);
		String successMessage = environment.getProperty("API.ORDER_DETAIL_INSERT_SUCCESS") + orderDto.getOrderDetailId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/order-details")
	public ResponseEntity<String> updateOrder(@RequestBody OrderDetailDTO orderDto) throws InvalidDataException {
		orderService.updateOrderDetail(orderDto);
		String successMessage = environment.getProperty("API.ORDER_DETAIL_UPDATE_SUCCESS")+orderDto.getOrderDetailId();
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PutMapping(value = "/order-details/{orderId}/{orderStatus}")
	public ResponseEntity<String> updateOrderDetailStatus(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable("orderId") Integer orderId,
			@PathVariable("orderStatus") OrderStatus orderStatus) throws InvalidDataException {
		orderService.updateOrderDetailStatus(orderId, orderStatus);
		String successMessage = environment.getProperty("API.ORDER_DETAIL_UPDATE_SUCCESS")+orderId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/order-details/{orderId}")
	public ResponseEntity<String> deleteOrderDetail(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDetailDTO orders = orderService.deleteOrderDetail(orderId);
		String successMessage = environment.getProperty("API.ORDER_DETAIL_DELETE_SUCCESS")+orderId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	
	@PutMapping(value = "/order-details/cancel/{orderId}")
	public ResponseEntity<OrderDetailDTO> cancelOrderDetail(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDetailDTO orders = orderService.cancelOrderDetail(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order-details/customer/{customerId}")
	public ResponseEntity<List<OrderDetailDTO>> showAllOrdersByCustomer(@Min(value = 1, message = "customer id should be grater than or equal to 1") @PathVariable Integer customerId)
			throws InvalidDataException {
		List<OrderDetailDTO> orderList = orderService.showAllOrderDetailssByCustomerId(customerId);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	@GetMapping(value = "/order-details/orderDate/{date}")
	public ResponseEntity<List<OrderDetailDTO>> showAllOrderDetailsByOrderDate(@PastOrPresent(message = "Order date should be past or present to current date") @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
			throws InvalidDataException {
		List<OrderDetailDTO> orderList = orderService.showAllOrderDetailsByOrderDate(date);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	@GetMapping(value = "/order-details/dispatchDate/{date}")
	public ResponseEntity<List<OrderDetailDTO>> showAllOrderDetailsByDispatchDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
			throws InvalidDataException {
		List<OrderDetailDTO> orderList = orderService.showAllOrderDetailsByDispatchDate(date);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
}
