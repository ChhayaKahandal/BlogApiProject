package com.chhaya.blog.services;

import java.util.List;

import com.chhaya.blog.payloads.UserDto;

public interface UserService
{
	//we are taking user from UserDto class.
	UserDto createUser(UserDto user);
	//which user details we want to update those user and userid we are taking from UserDto.
	UserDto updateUser(UserDto user,Integer userId);
	//get the particular user by id.
    UserDto getUserById(Integer userId);
    //get the all users
    List<UserDto> getallUsers();
    //delet the user 
    void deleteUser(Integer userId);
    
}
