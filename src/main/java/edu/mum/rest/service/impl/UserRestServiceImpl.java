package edu.mum.rest.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.mum.aspect.annotation.Logging;
import edu.mum.domain.User;
import edu.mum.rest.RestHttpHeader;
import edu.mum.rest.service.UserRestService;

@Component
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	RestHttpHeader restHttpHeader;
	
	public List<User> findAll() {
		
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		return Arrays.asList(restTemplate.exchange("http://localhost:8080/TeamSparkRest/users/", HttpMethod.GET, restHttpHeader.getHttpEntity(), User[].class).getBody());
 	}

	@Logging
	public User findOne(Long index) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
 		return (restTemplate.exchange("http://localhost:8080/TeamSparkRest/users/"+ index, HttpMethod.GET, restHttpHeader.getHttpEntity(), User.class).getBody());
	}

	@Logging
	public User save(User user) {
		RestTemplate restTemplate = restHttpHeader.getRestTemplate();
		HttpEntity<User> httpEntity = new HttpEntity<User>(user, restHttpHeader.getHttpHeaders());
		restTemplate.postForObject("http://localhost:8080/TeamSparkRest/users/add/", httpEntity, User.class);
		return null;
	}

}
