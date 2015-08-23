package com.anand.cpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.cpm.bean.Deal;
import com.anand.cpm.service.DealService;

@RestController
public class DealController {

	@Autowired
	private DealService dealService;
	
	@RequestMapping(value="deals")
	public List<Deal> getAllDeals() {
		return dealService.getAllDeals();
	}
	
	@RequestMapping(value="getInfo")
	public String getInfo() {
		return "CPM MICRO SERVICE SAMPLE";
	}
	
}
