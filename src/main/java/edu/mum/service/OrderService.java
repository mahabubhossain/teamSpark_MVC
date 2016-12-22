package edu.mum.service;

import java.util.List;

import edu.mum.domain.Order;
 
public interface OrderService {

	public void save(Order order);
  	
	public List<Order> findAll();

	public Order findOne(Long id);

	//public Order findByGraph(Long id);

	//public Order update(Order order);

	public void updateAdjunct();
 
}
