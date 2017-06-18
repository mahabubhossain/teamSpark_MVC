package edu.mum.rest.service;

import org.springframework.stereotype.Component;

import edu.mum.domain.Address;

@Component
public interface AddressRestService {

 	public Address findOne(Long index);

	public Address save(Address address);

}
