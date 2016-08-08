package com.anand.batch.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {
	
    //@Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create()
        		//.driverClassName(environment.getProperty("spring.datasource.driverClassName"))
        		//.url(environment.getProperty("spring.datasource.url"))
        		//.username(environment.getProperty("spring.datasource.username"))
        		//.password(environment.getProperty("spring.datasource.password"))
        		.build();
    	//return new EmbeddedDatabaseBuilder().build();
    }

    /*@Bean(name = "jobDataSource")
    @ConfigurationProperties(prefix="job.datasource")
    public DataSource jobDataSource(){
        return DataSourceBuilder.create()
        		//.url(url)
        		.build();
    	//return new EmbeddedDatabaseBuilder().build();
    }   */
}