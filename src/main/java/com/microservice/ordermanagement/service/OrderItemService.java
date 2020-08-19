package com.microservice.ordermanagement.service;

import org.springframework.stereotype.Service;

import com.microservice.ordermanagement.model.OrderItem;

public interface OrderItemService {

	OrderItem createOrderItem(OrderItem order);

	 OrderItem getOrderItem(String code);

}
