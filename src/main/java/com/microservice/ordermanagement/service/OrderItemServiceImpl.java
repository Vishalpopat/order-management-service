package com.microservice.ordermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.microservice.ordermanagement.exception.CustomException;
import com.microservice.ordermanagement.exception.ErrorEnum;
import com.microservice.ordermanagement.model.OrderItem;
import com.microservice.ordermanagement.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		OrderItem save = orderItemRepository.save(orderItem);
		return save;
	}

	@Override
	public OrderItem getOrderItem(String code) {
		Optional<OrderItem> findByCode = orderItemRepository.findByCode(code);
		if (findByCode.isEmpty()) {
			throw new CustomException(ErrorEnum.ORDER_ITEM_NOT_FOUND.getErrMsg() + ": " + code,
					ErrorEnum.ORDER_ITEM_NOT_FOUND.getErrCode(), HttpStatus.NOT_FOUND);
		}
		return findByCode.get();
	}

}
