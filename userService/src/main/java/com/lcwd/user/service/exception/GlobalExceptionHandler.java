package com.lcwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.user.service.payload.ApiResponse;
import com.lcwd.user.service.payload.ApiResponse.ApiResponseBuilder;

//A REST API developer will have two requirements related to error handling.
//1.Common place for Error handling
//2.Similar Error Response body with a proper HTTP status code across APIs
//Both requirements can be addressed by using RestControllerAdvice annotation in Spring.
//If customer does not exist in DB , I want to throw error message with http Status 404.
//To achieve this, we have to define global exception handler class with annotation @RestControllerAdvice


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)//this exception will be handles if it occurs anywhere
	public ResponseEntity<ApiResponse> handleResourceNotFroundException(ResourceNotFoundException ex){
		
		String message=ex.getMessage();      //msg success but Rsc not found
		//beautiful object creation and pattern usin g builder
       ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}
