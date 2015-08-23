package com.anand.cpm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.anand.cpm.bean.Deal;
import com.anand.cpm.service.DealService;

@RestController
public class DealController {
	
	@Autowired
	private RestTemplate restTemplate;

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
	
	@RequestMapping(value="/getInfo/{message}", method = RequestMethod.GET)
	public String getInfoMessage(@PathVariable("message") String message) {
		return "CPM MICRO SERVICE SAMPLE : " + message;
	}
	
	@RequestMapping(value="/getInfo/pcl", method = RequestMethod.GET)
	public String getPclInfo() {
		
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() { };
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://PCL-MICRO-SERVICES/getInfo", HttpMethod.GET, null, responseType);
		
		String messageFromPcl = responseEntity.getBody();
		
		return "CPM MICRO SERVICE SAMPLE :: Message from Pcl to CPM is ::  " + messageFromPcl;
	}
	
	@RequestMapping(value="/getInfo/pcl/{message}", method = RequestMethod.GET)
	public String getPclInfoMessage(@PathVariable("message") String message) {
		
		ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() { };
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://PCL-MICRO-SERVICES/getInfo/{message}", HttpMethod.GET, null, responseType, (Object)"temp-message-for-pcl-from-cpm");
		
		String messageFromPcl = responseEntity.getBody();
		
		return "CPM MICRO SERVICE SAMPLE :: Message from Pcl to CPM is ::  " + messageFromPcl;
	}
	
}
