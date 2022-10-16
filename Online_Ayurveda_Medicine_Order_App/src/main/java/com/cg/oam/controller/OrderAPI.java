package com.cg.oam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.dto.AdminDTO;
import com.cg.oam.dto.OrderDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.service.IAdminService;
import com.cg.oam.service.IOrderService;

@RestController
@RequestMapping(value = "/oam/ordersection")
public class OrderAPI {
	@Autowired
	IOrderService orderService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/orders")
	public ResponseEntity<List<OrderDTO>> getAllOrders() throws InvalidDataException {
		List<OrderDTO> orders = orderService.viewAllOrder();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}
