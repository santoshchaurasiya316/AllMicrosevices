package com.lcwd.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {

	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();            //we can also create our own configuration file
//	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
