package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

	@Bean
	@LoadBalanced  //if multiple instances(services like-HOTEL-SERVICE) are available then distributing load using name of services
	public RestTemplate restTemplate() {
		return new RestTemplate();      
   }
}
