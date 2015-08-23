package com.anand.cpm.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("PCL-MICRO-SERVICES")
public interface PclClient {
	@RequestMapping(method = RequestMethod.GET, value = "/getInfo")
	String getPclInfo();
	
	@RequestMapping(method = RequestMethod.GET, value = "/getInfo/{message}")
	String getPclInfoMessage(@PathVariable("userId") String userId);
}
