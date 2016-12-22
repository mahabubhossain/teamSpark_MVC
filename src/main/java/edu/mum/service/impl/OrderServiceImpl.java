package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.aspect.annotation.Logging;
import edu.mum.dao.OrderDao;
import edu.mum.domain.Order;

@Service
@Transactional 
public class OrderServiceImpl implements edu.mum.service.OrderService {
	
 	@Autowired
	//private OrderRestService orderRestService;
 	private OrderDao orderDao;


 	@Logging
   public void save( Order confirmedOrder) {  		
 		//orderRestService.save(userCredentials);
 		orderDao.save(confirmedOrder);
 	}
  	
 	@Logging
 	public Order findOne(Long id) {
 		//return orderRestService.findOne(id);
 		return orderDao.findOne(id);
 	}

  	/*public Order findByGraph(Long id) {
  		
  		return orderDao.findByGraph(id);
  	}*/
  	
	public List<Order> findAll() {
		//return (List<Order>)orderRestService.findAll();
		return (List<Order>)orderDao.findAll();
	}
	
	//@Logging
	/*public Order update(Order order) {
		this.updateAdjunct();
	    System.out.println("**********         UpdateAdjunct called from within Same Class - not Logged!       **********     ");
		return orderRestService.update(order);
	}*/

	// Never gets logged when called from update - 
	// BECAUSE of  Method level proxy "gotcha"
	
	@Logging
	public void updateAdjunct() {
		return ;
	}

	
 
}
