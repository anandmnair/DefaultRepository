package com.anand.ws;

import javax.xml.ws.Endpoint;

import org.springframework.util.Assert;

import com.anand.ws.service.impl.HelloServiceImpl;

public class MainWebService {
	public static void main(String[] args) {
		Endpoint endpoint=Endpoint.publish("http://localhost:8080/hello", new HelloServiceImpl());
		Assert.isTrue(endpoint.isPublished());
		
		
		
	}
}
