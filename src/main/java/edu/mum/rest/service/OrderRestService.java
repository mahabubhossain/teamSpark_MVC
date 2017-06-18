package edu.mum.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.mum.domain.Order;

@Component
public interface OrderRestService {

 	public List<Order> findAll();

	public Order findOne(Long index);

	public Order save(Order order);

}
