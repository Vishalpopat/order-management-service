package com.microservice.ordermanagement.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.ordermanagement.client.OrderItemClient;
import com.microservice.ordermanagement.exception.ApiError;
import com.microservice.ordermanagement.exception.CustomException;
import com.microservice.ordermanagement.exception.ErrorEnum;
import com.microservice.ordermanagement.model.Order;
import com.microservice.ordermanagement.model.OrderItem;
import com.microservice.ordermanagement.repository.OrderRepository;

import feign.FeignException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemClient orderItemClient;

	@Override
	public Order createOrder(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		Double total = 0d;
		for (int i = 0; i < orderItems.size(); i++) {
			try {
				OrderItem order2 = orderItemClient.getOrder(orderItems.get(i).getCode());
			} catch (FeignException ex) {
				String contentUTF8 = ex.contentUTF8();
				try {
					ApiError readValue = new ObjectMapper().readValue(contentUTF8, ApiError.class);
					throw new CustomException(readValue.getMessage(), readValue.getErrorCode(), readValue.getStatus());
				} catch (JsonProcessingException e) {
					log.error("Error in JsonProcessingException:{}", e);
					throw ex;
				}
			}
		}
		order.setTotal(total);
		order.setOrderDate(ZonedDateTime.now());
		Order save = orderRepository.save(order);
		return save;
	}

	@Override
	public Order getOrder(String id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isEmpty()) {
			throw new CustomException(ErrorEnum.ORDER_NOT_FOUND.getErrMsg() + ": " + id,
					ErrorEnum.ORDER_NOT_FOUND.getErrCode(), HttpStatus.NOT_FOUND);
		}
		return optionalOrder.get();
	}

}
