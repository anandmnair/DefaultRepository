package com.anand.cpm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringCloudApplication
@EnableFeignClients
@EnableZuulProxy
public class CpmMicroserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpmMicroserviceDemoApplication.class, args);
    }
    
}
