package com.chhaya.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chhaya.blog.payloads.ApiResponse;
import com.chhaya.blog.payloads.UserDto;
import com.chhaya.blog.services.UserService;
import com.chhaya.blog.models.User;

@RestController
@RequestMapping("/api/users")
public class UserController
{
	@Autowired
	private UserService userService;
	
     //Post:Create user
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
	{
		UserDto createdUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto,
				HttpStatus.CREATED);
	}
	 //Put:update user
	//here we passing "path uri variable i.e. userId,jya user che details update kryche ahe to.
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable("userId") int uId)//userdto mdhe user cha new data yeil means update krnyasathi new data.
	{
		UserDto updatedUser=this.userService.updateUser(userdto, uId);//here we new details of user(userdto) and Id of user which we have to update with that new details.
		return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
		//return ResponseEntity.ok(updatedUser); this second way of above line
	}
	
	 
	 //Delet:delete user
	@DeleteMapping("/deletUser/{userId}")
	//for delet we dont return anything but here we return somethng
	//ResponseEntity<?> ->when we dont know type then we write ?.
	//ApiResponse:it is class
	public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") int uId)//@PathVariable("userId") Integer uId =>jevha apan vrti uri mdhal name(userId) ani ikdch name(uid) vegl deto tevha ha format use krne.
	{
		this.userService.deleteUser(uId);
	    //return new  ResponseEntity(Map.of("message","User deleted Successfully"),HttpStatus.OK);//we can use this way also without ApiResponse class
		return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}
	
	 //Get:get Alluser
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getallUsers());
		//List<UserDto> getAllUser=this.userService.getallUsers();
		
		//return new ResponseEntity<List<UserDto>>(getAllUser,HttpStatus.OK);
	}
	
	
	 //Get:get user by id
	
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") int uId)
		{
			//return ResponseEntity.ok(this.userService.getUserById(userId));
			//User getUser=this.userService.getUserById(userId);
			
			return new ResponseEntity<UserDto>(this.userService.getUserById(uId),HttpStatus.OK);
		}
		
	
	
	
	
	
	
}
