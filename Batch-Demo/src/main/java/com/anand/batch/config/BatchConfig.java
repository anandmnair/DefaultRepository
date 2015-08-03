package com.anand.batch.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.anand.batch.bean.DummyItem;
import com.anand.batch.listener.BasicJobExecutionListener;
import com.anand.batch.listener.BasicStepExecutionListener;
import com.anand.batch.mapper.ExitCodeMapperImpl;
import com.anand.batch.processor.DummyProcessor;
import com.anand.batch.reader.DummyReader;
import com.anand.batch.writer.DummyWriter;

@Configuration
@EnableBatchProcessing
@PropertySource(value="classpath:batch.properties")
public class BatchConfig {
	
	@Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;
   
    @Autowired
    private Environment environment;

	@Bean
	public Job dummyJob() {
		return jobs.get("dummyJob")
				.incrementer(new RunIdIncrementer())
				.start(dummyStep())
				.listener(basicJobExecutionListener())
				.build();
	}
	
	@Bean
	public Step dummyStep() {
		return steps.get("dummyStep")
				.<DummyItem,DummyItem>chunk(2)
				.reader(dummyReader())
				.processor(dummyProcessor())
				.writer(dummyWriter())
				.listener(basicStepExecutionListener())
				.build();
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
		Map<String,String> propertyMap=new LinkedHashMap<String,String>();
		propertyMap.put("dummy.properties", environment.getProperty("dummy.properties"));
		BasicJobExecutionListener basicJobExecutionListener = new BasicJobExecutionListener();
		basicJobExecutionListener.setPropertyMap(propertyMap);
		return basicJobExecutionListener;
	}

}
