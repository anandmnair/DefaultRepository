package com.anand.batch.validator;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class Validator implements JobParametersValidator {
	
	private static final Log logger = LogFactory.getLog(Validator.class);
	
	private static AtomicInteger count= new AtomicInteger(0);

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		System.out.println("******* validators ********" + parameters);
		//RuntimeException exception = new RuntimeException("***validator***"  + count.incrementAndGet());
		//logger.error("***validator***",exception);
	}
}