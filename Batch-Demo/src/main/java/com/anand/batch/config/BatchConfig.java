package com.anand.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anand.batch.bean.DummyItem;
import com.anand.batch.mapper.ExitCodeMapperImpl;
import com.anand.batch.processor.DummyProcessor;
import com.anand.batch.reader.DummyReader;
import com.anand.batch.writer.DummyWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

	@Bean
	public Job dummyJob() {
		return jobs.get("dummyJob")
				.incrementer(new RunIdIncrementer())
				.start(dummyStep())
				.build();
	}
	
	@Bean
	public Step dummyStep() {
		return steps.get("dummyStep")
				.<DummyItem,DummyItem>chunk(2)
				.reader(dummyReader())
				.processor(dummyProcessor())
				.writer(dummyWriter())
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
}
