package com.anand.pcl.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@RequestMapping(value="/getInfo")
	public String getInfo() {
		return "PCL MICRO SERVICE SAMPLE";
	}
	
	@RequestMapping(value="/getInfo/{message}", method = RequestMethod.GET)
	public String getInfoMessage(@PathVariable("message") String message) {
		return "PCL MICRO SERVICE SAMPLE : " + message;
	}
	
}
