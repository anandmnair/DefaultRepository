package com.anand.batch.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

public class CustomeBatchConfigurer implements BatchConfigurer {
	
	private static final Log logger = LogFactory.getLog(CustomeBatchConfigurer.class);

	private DataSource dataSource;
	private PlatformTransactionManager transactionManager;
	private JobRepository jobRepository;
	private JobLauncher jobLauncher;
	private JobExplorer jobExplorer;

	private boolean isMapJobRepository = false;
	
	@Autowired(required = false)
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.transactionManager = new DataSourceTransactionManager(dataSource);
	}

	public CustomeBatchConfigurer() {}

	public CustomeBatchConfigurer(DataSource dataSource, boolean isMapJobRepository) {
		this.isMapJobRepository=isMapJobRepository;
		setDataSource(dataSource);
	}

	@Override
	public JobRepository getJobRepository() {
		return jobRepository;
	}

	@Override
	public PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	@Override
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	@Override
	public JobExplorer getJobExplorer() {
		return jobExplorer;
	}

	@PostConstruct
	public void initialize() {
		try {
			if(dataSource == null || isMapJobRepository ) {
				logger.warn("No datasource was provided...using a Map based JobRepository");

				if(this.transactionManager == null) {
					this.transactionManager = new ResourcelessTransactionManager();
				}

				MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean(this.transactionManager);
				jobRepositoryFactory.afterPropertiesSet();
				this.jobRepository = jobRepositoryFactory.getObject();

				MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
				jobExplorerFactory.afterPropertiesSet();
				this.jobExplorer = jobExplorerFactory.getObject();
			} else {
				this.jobRepository = createJobRepository();

				JobExplorerFactoryBean jobExplorerFactoryBean = new JobExplorerFactoryBean();
				jobExplorerFactoryBean.setDataSource(this.dataSource);
				jobExplorerFactoryBean.afterPropertiesSet();
				this.jobExplorer = jobExplorerFactoryBean.getObject();
			}

			this.jobLauncher = createJobLauncher();
		} catch (Exception e) {
			throw new BatchConfigurationException(e);
		}
	}

	protected JobLauncher createJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	protected JobRepository createJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(dataSource);
		factory.setTransactionManager(transactionManager);
		factory.afterPropertiesSet();
		return  factory.getObject();
	}
}
