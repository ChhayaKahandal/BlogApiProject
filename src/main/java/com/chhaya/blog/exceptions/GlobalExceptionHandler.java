package com.chhaya.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	  
	  
	  /*
	   When we creating user with invalid field data or empty null etc then this method will execute.
	    o/p
	   "password": "Password must be min of 3 chars and max of 10 chars",
       "name": "Username must be min of 4 character",
       "email": "Email address is not valid..!!"
	   MethodArgumentNotValidException it is inbuilt exception class
	   */
	  @ExceptionHandler(MethodArgumentNotValidException.class)//which type of exception we have to handle.then give that class name.
      public ResponseEntity<Map<String,String>> handleMethodArguNotValidException(MethodArgumentNotValidException ex)
      {
		  
		  Map<String,String> errors=new HashMap<>();
		  //here we getting each field and error message regarding that field.
		//suppose name field la error ali tr tyachyasobtcha msg apn get kru
		  //and then we add that all field and their correspondindmeaages in map.
    	  ex.getBindingResult().getAllErrors().forEach((error)->
    	  {
    		 String fieldName=((FieldError)error).getField();//here we type casting Object error into Filed error.and storing that into FieldName .
    		 String message= error.getDefaultMessage();
    		 errors.put(fieldName, message);
    	  });
    	  return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
    	  
      }
}
