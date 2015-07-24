package com.anand.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anand.batch.config.BatchConfig;
import com.anand.batch.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { BatchDemoApplication.class, BatchConfig.class , TestConfig.class } )
public class BatchDemoApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void testBatch() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}

	@Test
	public void testStep() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("dummyStep");
		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}
}
