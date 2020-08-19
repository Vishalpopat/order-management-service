package com.microservice.ordermanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.ordermanagement.model.OrderItem;

@FeignClient(name ="orderItem", url="http://localhost:8081/v1")
public interface OrderItemClient {
	
	@PostMapping("/orderitems")
	public OrderItem createOrder(@RequestBody OrderItem orderItem);
	
	@GetMapping("/orderitems/{code}")
	public OrderItem getOrder(@PathVariable String code);

}
