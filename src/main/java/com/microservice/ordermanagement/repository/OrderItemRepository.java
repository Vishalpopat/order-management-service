package com.microservice.ordermanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ordermanagement.model.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, String>{

	Optional<OrderItem> findByCode(String code);
}
