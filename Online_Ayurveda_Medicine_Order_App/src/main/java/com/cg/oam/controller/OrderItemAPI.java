package com.cg.oam.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.dto.OrderDetailDTO;
import com.cg.oam.dto.OrderItemDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.IOrderItemRepository;
import com.cg.oam.service.IOrderDetailService;
import com.cg.oam.service.IOrderItemService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/oam")
@Validated
public class OrderItemAPI {
	@Autowired
	IOrderItemService orderService;

	/** The environment. */
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/order-items")
	public ResponseEntity<String> addOrderItem(@Valid @RequestBody OrderItemDTO order) throws InvalidDataException {
		OrderItemDTO orderDto = orderService.addOrderItem(order);
		String successMessage = environment.getProperty("API.ORDER_ITEM_INSERT_SUCCESS") + orderDto.getOrderItemId();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/order-items")
	public ResponseEntity<List<OrderItemDTO>> viewAllOrderItems() throws InvalidDataException {
		List<OrderItemDTO> orders = orderService.viewAllOrderItems();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order-items/{orderId}")
	public ResponseEntity<OrderItemDTO> viewOrderItemsById(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderItemDTO orders = orderService.viewOrderItemById(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order-items/order-details/{orderId}")
	public ResponseEntity<List<OrderItemDTO>> viewAllOrderItemsByOrderDetailId(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		List<OrderItemDTO> orders = orderService.showAllOrderItemsByOrderDetailId(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	@PutMapping(value = "/order-items")
	public ResponseEntity<String> updateOrderItem(@RequestBody OrderItemDTO orderDto) throws InvalidDataException {
		orderService.updateOrderItem(orderDto);
		String successMessage = environment.getProperty("API.ORDER_ITEM_UPDATE_SUCCESS")+orderDto.getOrderItemId();
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	@GetMapping(value = "/order-items/medicine/{medicineId}")
	public ResponseEntity<List<OrderItemDTO>> showAllOrderItemsByMedicine(@Min(value = 1, message = "medicine id should be grater than or equal to 1") @PathVariable Integer medicineId)
			throws InvalidDataException {
		List<OrderItemDTO> orderList = orderService.showAllOrderItemsByMedicineId(medicineId);
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}
	@DeleteMapping(value = "/order-items/{orderId}")
	public ResponseEntity<String> deleteOrderItem(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderItemDTO orders = orderService.deleteOrderItem(orderId);
		String successMessage = environment.getProperty("API.ORDER_ITEM_DELETE_SUCCESS")+orderId;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
