package com.anand.batch.listener;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class BasicJobExecutionListener implements JobExecutionListener {
	
	private static final Log log = LogFactory.getLog(BasicJobExecutionListener.class);
	
	private static final String BANNER_HEADER_BAR = "**************************************************************************************************************";
	private static final String BANNER_INFO_BAR       = " %s : %s ";
	
	private Map<String,String> propertyMap;
	
	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

	@BeforeJob
	public void beforeJob(JobExecution jobExecution){
		log.info(String.format(BANNER_HEADER_BAR));
		log.info(String.format(BANNER_INFO_BAR, "START : JOB", jobExecution.getJobInstance().getJobName()));
		log.info(String.format(BANNER_HEADER_BAR));
		if(propertyMap!=null) {
			log.info("Property Map : " + propertyMap);
		}
	}
	
	@AfterJob
	public void afterJob(JobExecution jobExecution) {
		log.info("Job Execution Details : " + jobExecution);
		log.info(String.format(BANNER_HEADER_BAR));
		log.info(String.format(BANNER_INFO_BAR, "END : JOB  ", jobExecution.getJobInstance().getJobName()));
		log.info(String.format(BANNER_HEADER_BAR));
	}
	
}
