package edu.mum.rest.service;

import org.springframework.stereotype.Component;

import edu.mum.domain.UserCredentials;

@Component
public interface UserCredentialsRestService {

 	//public List<UserCredentials> findAll();

	public UserCredentials findOne(String index);

	public UserCredentials save(UserCredentials member);

}
