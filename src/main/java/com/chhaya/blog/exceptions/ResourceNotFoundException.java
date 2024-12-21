package com.chhaya.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

//here we extending the unchecked type exception.thats why we extends RuntimeException
//this exception class is used in "UserServiceImpl" class's second method.If particular Id's user not found then throw this exception.
//getter & setters are optional
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException
{
	 String resourceName;
	 String fieldName;
	 long fieldValue;
	 
	 //constructor
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue)
	{
		
		super(String.format("%s not found with %s :%s",resourceName,fieldName,fieldValue));//%s not found with %s :%s ->"user not found with id :2"
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
	}
	 

}
