package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

	  //if multiple instances(services like-HOTEL-SERVICE) are available then distributing load using name of services
                   //so that we can call services by using there name like-HOTEL-SERVICE
	//becoz resttmplte is responsible to call all api like-restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/")
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();      
   }
}
