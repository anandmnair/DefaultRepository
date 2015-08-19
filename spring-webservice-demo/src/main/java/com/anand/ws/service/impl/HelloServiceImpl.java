package com.anand.ws.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.anand.ws.model.Person;
import com.anand.ws.service.HelloService;

@Component
@WebService
public class HelloServiceImpl implements HelloService {

	@WebMethod
	@Override
	public String sayHello(Person person) {
		return "Hello " + person;
	}

}
