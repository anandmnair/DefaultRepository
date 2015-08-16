package com.anand.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringMicroServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroServiceDemoApplication.class, args);
    }
}
