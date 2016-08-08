package com.anand.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.anand.batch.exception.NoDataFoundException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody String genericExceptionHandler(Exception e) {
		logger.error("Generic Exception... : ", e);
		return "INTERNAL_SERVER_ERROR"+e.getMessage();
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public @ResponseBody String noDataFoundExceptionHandler(NoDataFoundException e) {
		logger.error("No data Exception... : ", e);
		return "NoDataFoundException "+e.getMessage();
	}
	

}
