package com.anand.microservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.microservice.bean.Deal;
import com.anand.microservice.repository.DealRepository;
import com.anand.microservice.service.DealService;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	private DealRepository dealRepository;
	
	@Override
	public List<Deal> getAllDeals() {
		return dealRepository.findAll();
	}

}
