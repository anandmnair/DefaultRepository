package com.anand.cpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anand.cpm.client.PclClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class PclService {

	@Autowired 
	private PclClient pclClient;
	
	@HystrixCommand(fallbackMethod="getPclInfoFallback")
	public String getPclInfo(){
		return pclClient.getPclInfo();
	}
	
	public String getPclInfoFallback(){
		return "***** PclInfoFallback *****";
	}
	
	@HystrixCommand(fallbackMethod="getPclInfoMessageFallback")
	public String getPclInfoMessage(String message){
		return pclClient.getPclInfoMessage(message);
	}
	
	public String getPclInfoMessageFallback(String message){
		return "***** PclInfoFallback :: " + message + " *****";
	}
}
