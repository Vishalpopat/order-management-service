package com.microservice.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ordermanagement.model.OrderItem;
import com.microservice.ordermanagement.service.OrderItemService;

@RestController
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;

	@PostMapping("/orderitems")
	public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
		OrderItem createOrderItem = orderItemService.createOrderItem(orderItem);
		return new ResponseEntity<OrderItem>(createOrderItem, HttpStatus.CREATED);
	}

	@GetMapping("/orderitems/{code}")
	public ResponseEntity<OrderItem> getOrderItem(@PathVariable String code) {
		OrderItem orderItem = orderItemService.getOrderItem(code);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}
}
