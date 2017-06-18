package edu.mum.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.aspect.annotation.Logging;
import edu.mum.domain.Address;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.AddressRestService;

@Component
public class AddressRestServiceImpl implements AddressRestService {

	@Autowired
	RestHttpHeader restHttpHeader;
	
	public Address findOne(Long index) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
 		return (restTemplate.exchange("http://localhost:8080/TeamSparkRest/address/"+ index, HttpMethod.GET, restHttpHeader.getHttpEntity(), Address.class).getBody());
	}

	@Logging
	public Address save(Address address) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		HttpEntity<Address> httpEntity = new HttpEntity<Address>(address, restHttpHeader.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/TeamSparkRest/Address/add/", httpEntity, Address.class);
		return null;
	}

}
