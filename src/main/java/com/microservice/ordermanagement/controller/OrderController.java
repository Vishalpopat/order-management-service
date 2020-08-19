package com.microservice.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ordermanagement.model.Order;
import com.microservice.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order createOrder = orderService.createOrder(order);
		return new ResponseEntity<Order>(createOrder, HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable String id) {
		 Order order = orderService.getOrder(id);
		 return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}
