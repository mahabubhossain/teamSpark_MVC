package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.aspect.annotation.Logging;
import edu.mum.domain.Order;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.OrderRestService;

@Component
public class OrderRestServiceImpl  implements OrderRestService {

	@Autowired
	RestHttpHeader remoteApi;
	
	public List<Order> findAll() {
		
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/TeamSparkRest/orders/", 
				HttpMethod.GET, remoteApi.getHttpEntity(), Order[].class).getBody());
                               //remoteApi.getHttpEntity() - get HTTP headers [Authentication; JSON ACCEPT]
	}

	@Logging
	public Order findOne(Long index) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
 		return (restTemplate.exchange("http://localhost:8080/TeamSparkRest/orders/"+ index, 
 				HttpMethod.GET, remoteApi.getHttpEntity(), Order.class).getBody());
                                                           // Returns Product in Body HTTP Message 
	}

	@Logging
	public Order save(Order order) {
		RestTemplate restTemplate = remoteApi.getRestTemplate();
		HttpEntity<Order> httpEntity = new HttpEntity<Order>(order, remoteApi.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/TeamSparkRest/orders/add/", 
				        httpEntity, Order.class);
                                     // Product.class - Response type

 		return null;
	}

}
