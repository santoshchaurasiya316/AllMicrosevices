package com.lcwd.user.service.payload;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder 
@NoArgsConstructor
@AllArgsConstructor      
public class ApiResponse {
	 //The @Builder annotation produces complex builder APIs for your classes ,providing a way to 
    //build the object step-by-step and provide a method that will actually return the final Object.
	private String message;
	private boolean success;
	private HttpStatus status;
	
	
	
}
