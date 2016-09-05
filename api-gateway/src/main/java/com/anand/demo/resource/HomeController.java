package com.anand.demo.resource;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping(value="/home")
	public String home() {
		getUser();
		return "home ::" + new Date().toString();
	}
	
	@RequestMapping(value="/health")
	public String health() {
		getUser();
		return "running... ::" + new Date().toString();
	}
	
	@RequestMapping(value="/say/hello/{name}")
	public String sayHello(@PathVariable("name")String name) {
		getUser();
		return "hello " + name;
	}
	
	private void getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("----------------------------");
		if(authentication!=null){
			System.out.println("Name        : " + authentication.getName());
			System.out.println("Principal   : " + authentication.getPrincipal());
			System.out.println("Credentials : " + authentication.getCredentials());
			System.out.println("Authorities : " + authentication.getAuthorities());
		}
		else {
			System.out.println("authentication : null");
		}
		System.out.println("----------------------------");
	}
}
