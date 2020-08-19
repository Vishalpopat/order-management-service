package com.microservice.ordermanagement.service;

import com.microservice.ordermanagement.model.Order;

public interface OrderService {

	Order createOrder(Order order);
	
	Order getOrder(String id);

}
