package com.microservice.ordermanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ordermanagement.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String>{
	
	Optional<Order> findById(String id);
	
}
