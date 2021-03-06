package com.anand.batch.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.anand.batch.bean.DummyItem;
import com.anand.batch.listener.BasicJobExecutionListener;
import com.anand.batch.listener.BasicStepExecutionListener;
import com.anand.batch.mapper.ExitCodeMapperImpl;
import com.anand.batch.processor.DummyProcessor;
import com.anand.batch.reader.DummyReader;
import com.anand.batch.validator.Validator;
import com.anand.batch.writer.DummyWriter;

@Configuration
@EnableBatchProcessing
@PropertySource(value = "classpath:batch.properties")
public class BatchConfig {

	
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private Environment environment;
	
	@Value("${spring.batch.job.map.repository.enabled}")
	private boolean isMap;

	/*@Bean
	public BatchConfigurer batchConfigurer(@Qualifier("dataSource") DataSource dataSource) {
		CustomeBatchConfigurer batchConfigurer= new  CustomeBatchConfigurer(dataSource,isMap);
		return batchConfigurer;
	}*/
	
	@Bean
	public Job dummyJob() {
		return jobs.get("dummyJob").validator(new Validator()).incrementer(new RunIdIncrementer())
				.start(dummyStep())
				.listener(basicJobExecutionListener()).build();
	}

	@Bean
	public Step dummyStep() {
		return steps.get("dummyStep").allowStartIfComplete(true).<DummyItem, DummyItem> chunk(2).reader(dummyReader()).processor(dummyProcessor())
				.writer(dummyWriter()).listener(basicStepExecutionListener()).build();
	}

	@Bean
	public DummyReader dummyReader() {
		return new DummyReader();
	}

	@Bean
	public DummyProcessor dummyProcessor() {
		return new DummyProcessor();
	}

	@Bean
	public DummyWriter dummyWriter() {
		return new DummyWriter();
	}

	@Bean
	public ExitCodeMapper exitCodeMapper() {
		return new ExitCodeMapperImpl();
	}

	@Bean
	@StepScope
	public BasicStepExecutionListener basicStepExecutionListener() {
		return new BasicStepExecutionListener();
	}

	@Bean
	@JobScope
	public BasicJobExecutionListener basicJobExecutionListener() {
		Map<String, String> propertyMap = new LinkedHashMap<String, String>();
		propertyMap.put("dummy.properties", environment.getProperty("dummy.properties"));
		BasicJobExecutionListener basicJobExecutionListener = new BasicJobExecutionListener();
		basicJobExecutionListener.setPropertyMap(propertyMap);
		return basicJobExecutionListener;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
	
}
