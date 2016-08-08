package com.anand.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anand.batch.bean.Deal;
import com.anand.batch.exception.NoDataFoundException;
import com.anand.batch.repository.DealRepository;

@RestController
@RequestMapping(value="/job")
public class JobController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@Autowired
	private DealRepository dealRepository;
	

	@RequestMapping(value="/health", method=RequestMethod.GET)
	public String health() {
		return "running..!";
	}
	
	@RequestMapping(value="/launch/{jobName}", method=RequestMethod.GET)
	public String launch(@PathVariable("jobName")String jobName) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder().addString("key", jobName).toJobParameters());
		
		return jobExecution.getExitStatus().getExitCode();
	}
	
	
	@RequestMapping(value="/ex1/{message}", method=RequestMethod.GET)
	public String ex1(@PathVariable("message")String message) {
		if("E".equals(message)) {
			throw new NoDataFoundException();
		}
		return "ex1 " + message;
	}
	
	@RequestMapping(value="/ex2/{message}", method=RequestMethod.GET)
	public String ex2(@PathVariable("message")String message) {
		if("E".equals(message)) {
			throw new NoDataFoundException("there is no data available");
		}
		return "ex2 " + message;
		
	}
	
	@RequestMapping(value="/ex3/{message}", method=RequestMethod.GET)
	public String ex3(@PathVariable("message")String message) {
		if("E".equals(message)) {
			throw new RuntimeException("there is no data available");
		}
		return "ex2 " + message;
		
	}
	
	@RequestMapping(value="/deal/{id}", method=RequestMethod.GET)
	public @ResponseBody Deal getDeal(@PathVariable("id")Long id) {
		Deal deal = dealRepository.findOne(id);
		if(deal==null) {
			deal = newDeal(id);
			deal = dealRepository.save(deal);
		}
		return deal;
	}
	
	@RequestMapping(value="/deal/name/{dealName}", method=RequestMethod.GET)
	public Page<Deal> getDeal(@PathVariable("dealName")String dealName) {
		Pageable pageable = new PageRequest(0, 10);
		return dealRepository.findByDealNameLike(dealName,pageable);
	}
	
	private Deal newDeal(Long id) {
		return new Deal(id, "D"+id, "Deal"+id);
	}
	
}
