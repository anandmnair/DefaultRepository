package com.anand.pcl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	@RequestMapping(name="/getInfo")
	public String getInfo() {
		return "PCL MICRO SERVICE SAMPLE";
	}
	
}