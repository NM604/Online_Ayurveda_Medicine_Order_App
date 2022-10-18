package com.cg.oam.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

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

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.dto.OrderStatus;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IAdminService;
import com.cg.oam.service.IOrderService;

@RestController
@RequestMapping(value = "/oam/ordersection")
@Validated
public class OrderAPI {
	@Autowired
	IOrderService orderService;

	@Autowired
	Environment environment;


	@PostMapping(value = "/orders")
	public ResponseEntity<String> addOrder(@Valid @RequestBody OrderDTO order) throws InvalidDataException {
		OrderDTO orderDto = orderService.addOrder(order);
		String successMessage = environment.getProperty("API.ORDER_INSERT_SUCCESS") + orderDto.getOrderId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@GetMapping(value = "/orders")
	public ResponseEntity<List<OrderDTO>> getAllOrders() throws InvalidDataException {
		List<OrderDTO> orders = orderService.viewAllOrder();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/medicine/{medicineId}")
	public ResponseEntity<List<OrderDTO>> showAllOrdersByMedicineId(@Min(value = 1, message = "Medicine id should be grater than or equal to 1") @PathVariable Integer medicineId) throws InvalidDataException {
		List<OrderDTO> orderList = orderService.showAllOrdersByMedicineId(medicineId);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/customer/{customerId}")
	public ResponseEntity<List<OrderDTO>> showAllOrdersByCustomer(@Min(value = 1, message = "customer id should be grater than or equal to 1") @PathVariable Integer customerId)
			throws InvalidDataException {
		List<OrderDTO> orderList = orderService.showAllOrdersByCustomerId(customerId);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/orderDate/{date}")
	public ResponseEntity<List<OrderDTO>> showAllOrdersByOrderDate(@PastOrPresent(message = "Order date should be past or present to current date") @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
			throws InvalidDataException {
		List<OrderDTO> orderList = orderService.showAllOrdersByOrderDate(date);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/dispatchDate/{date}")
	public ResponseEntity<List<OrderDTO>> showAllOrdersByDispatchDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
			throws InvalidDataException {
		List<OrderDTO> orderList = orderService.showAllOrdersByDispatchDate(date);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/{orderId}")
	public ResponseEntity<OrderDTO> getAllOrdersById(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDTO orders = orderService.viewOrderById(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/orderStatus/{orderStatus}")
	public ResponseEntity<List<OrderDTO>> getAllOrdersByStatus(@PathVariable OrderStatus orderStatus)
			throws InvalidDataException {
		List<OrderDTO> orderList = orderService.viewOrderByStatus(orderStatus);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@PutMapping(value = "/orders/{orderDto}")
	public ResponseEntity<String> updateOrder(@RequestBody OrderDTO orderDto) throws InvalidDataException {
		orderService.updateOrder(orderDto);
		String successMessage = environment.getProperty("API.ORDER_UPDATE_SUCCESS")+orderDto.getOrderId();
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	@PutMapping(value = "/orders/{orderId}/{orderStatus}")
	public ResponseEntity<String> updateOrderStatus(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable("orderId") Integer orderId,
			@PathVariable("orderStatus") OrderStatus orderStatus) throws InvalidDataException {
		orderService.updateOrderStatus(orderId, orderStatus);
		String successMessage = environment.getProperty("API.UPDATE_SUCCESS")+orderId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/orders/calculateCost/{orderId}")
	public ResponseEntity<Double> calculateTotalCost(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		Double orders = orderService.calculateTotalCost(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PutMapping(value = "/orders/cancel/{orderId}")
	public ResponseEntity<OrderDTO> cancelOrder(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDTO orders = orderService.cancelOrder(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/orders/{orderId}")
	public ResponseEntity<String> deleteOrder(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDTO orders = orderService.deleteOrder(orderId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS")+orderId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}