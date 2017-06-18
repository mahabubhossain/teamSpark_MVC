package edu.mum.rest.service;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.mum.domain.User;

@Component
public interface UserRestService {

 	public List<User> findAll();

	public User findOne(Long index);

	public User save(User user);

}
