package com.anand.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.anand.ws.model.Person;

@WebService
public interface HelloService {
	
	@WebMethod
	public String sayHello(Person person);
}
