package com.anand.pcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PclMicroserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PclMicroserviceDemoApplication.class, args);
    }
}
