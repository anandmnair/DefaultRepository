package com.anand.cpm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anand.cpm.bean.Deal;
import com.anand.cpm.repository.DealRepository;
import com.anand.cpm.service.DealService;

@Service
public class DealServiceImpl implements DealService {

	@Autowired
	private DealRepository dealRepository;
	
	@Override
	public List<Deal> getAllDeals() {
		return dealRepository.findAll();
	}

}
