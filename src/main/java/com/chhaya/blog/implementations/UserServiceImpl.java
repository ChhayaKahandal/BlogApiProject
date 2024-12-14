package com.chhaya.blog.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chhaya.blog.models.User;
import com.chhaya.blog.payloads.UserDto;
import com.chhaya.blog.repositories.UserRepo;
import com.chhaya.blog.services.UserService;

public class UserServiceImpl implements UserService
{
	//for permoning all below operation like create,update,delet etc we want Repository.thats why we use below
	@Autowired
	private UserRepo userrepo;

	@Override
	public UserDto createUser(UserDto userdto)
	{
		//here we call the method to convert the Userdto to User.and store that User into "user" object.
		User user=this.dtoToUser(userdto);
		//now here we are saving above user.
		User saveduser=userrepo.save(user);
		//here now we converting User again into UserDto.and returning that saveduser.
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) 
	{
		
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId)
	{
		
		return null;
	}

	@Override
	public List<UserDto> getallUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) 
	{
		
		
	}
	
	//In first method,when we have to pass the User in save() method then we want User,not UserDto ,but we have UserDto.so we converting UserDto into User.
	//this method is for changing/converting the UserDto into the User
	public User dtoToUser(UserDto userdto)
	{
		User user=new User();
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		return user;
		
	}
	
	//this method is for changing/converting the User into the UserDto
	public UserDto userToDto(User user)
	{
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
