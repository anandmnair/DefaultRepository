package com.anand.batch.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Value;

public class BasicStepExecutionListener {
	
	private static final Log log = LogFactory.getLog(BasicStepExecutionListener.class);
	
	private static final String BANNER_HEADER_BAR = "--------------------------------------------------------------------------------------------------------------";
	private static final String BANNER_INFO_BAR       = " %s : %s ";

	@Value("#{stepExecution['stepName']}")
	private String stepName;
	
	@Value("#{stepExecution}")
	private StepExecution stepExecution;
	
	@BeforeStep
	public void beforeStep(){
		log.info(String.format(BANNER_HEADER_BAR));
		log.info(String.format(BANNER_INFO_BAR, "START", stepName));
		log.info(String.format(BANNER_HEADER_BAR));
	}
	
	@AfterStep
	public ExitStatus afterStep() {
		log.info("Step Execution Details : " + stepExecution);
		log.info(String.format(BANNER_HEADER_BAR));
		log.info(String.format(BANNER_INFO_BAR, "END  ", stepName));
		log.info(String.format(BANNER_HEADER_BAR));
		return null;
	}
	
}
