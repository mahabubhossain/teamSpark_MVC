package edu.mum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.dao.UserCredentialsDao;
import edu.mum.domain.Authority;

@Service
public class AuthorityServiceImpl implements edu.mum.service.AuthorityService {
	
 	@Autowired
	//private UserCredentialsRestService userCredentialsRestService;
 	private UserCredentialsDao userCredentialsDao;

	@Override
	public Authority findAuthority(String id) {
		// TODO Auto-generated method stub
		return userCredentialsDao.findAuthority(id);
	}

 	
    
	 
 
}
