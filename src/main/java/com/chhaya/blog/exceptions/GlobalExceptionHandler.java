package com.chhaya.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chhaya.blog.payloads.ApiResponse;

@RestControllerAdvice//ControllerAdvice is represent the class as Exception Handler.here we use RestControllerAdvice beacuse we are working with rest app.
public class GlobalExceptionHandler 
{
	/*
	 When we find the user which is not present in Databse then this method gets call.
	 and output will be:
	  "message": "user not found with id :167",
      "success": false
      
      Note:ResourceNotFoundException and ApiResponse are class which we have created.
	 */
	  @ExceptionHandler(ResourceNotFoundException.class)//which type of exception we have to handle.then give that class name.
      public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
      {
    	  String message=ex.getMessage();
    	  ApiResponse apiResponse=new ApiResponse(message,false);
    	  return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    	  
      }
}
