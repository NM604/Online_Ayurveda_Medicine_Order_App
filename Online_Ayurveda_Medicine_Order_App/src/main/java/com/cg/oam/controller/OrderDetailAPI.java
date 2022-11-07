package com.cg.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IOrderDetailService;
import javax.validation.constraints.Min;
import com.cg.oam.dto.OrderDetailDTO;

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
	public ResponseEntity<OrderDetailDTO> getAllOrdersById(@Min(value = 1, message = "order id should be grater than or equal to 1") @PathVariable Integer orderId) throws InvalidDataException {
		OrderDetailDTO orders = orderService.viewOrderDetailById(orderId);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
