package com.chhaya.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//this class is used in UserController class's delete method.
//user delete kelyannatr response mdhe msg show honyasathi use kela ahe.
public class ApiResponse 
{
	private String message;
	private boolean success;

}
