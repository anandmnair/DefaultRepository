package com.anand.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.microservice.bean.Deal;
import com.anand.microservice.service.DealService;

@RestController
public class DealController {

	@Autowired
	private DealService dealService;
	
	@RequestMapping(name="/deals")
	public List<Deal> getAllDeals() {
		return dealService.getAllDeals();
	}
	
}
