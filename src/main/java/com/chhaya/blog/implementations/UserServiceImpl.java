package com.chhaya.blog.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaya.blog.exceptions.ResourceNotFoundException;
import com.chhaya.blog.models.User;
import com.chhaya.blog.payloads.UserDto;
import com.chhaya.blog.repositories.UserRepo;
import com.chhaya.blog.services.UserService;
@Service
public class UserServiceImpl implements UserService
{
	//for permoning all below operation like create,update,delet etc we want Repository.thats why we use below.
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;//here we injecting the modelmapper object which is given by main class using bean.we use this for conversion of object

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
	public UserDto updateUser(UserDto userdto, int userId) 
	{
		//If user of that id not found then throw this exception.
		User user=this.userrepo.findById(userId)
				       .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		//if that user find then update below values.
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		//we saving/store that user as updatesuser.
		User updateduser=this.userrepo.save(user);
		//here we calling the method userToDto and converting User to UserDto
		UserDto userDto1=this.userToDto(updateduser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(int userId)
	{
		//here we finding user by Id.
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getallUsers()
	{
		List<User>usersli=this.userrepo.findAll();
		//here we converting the list of Users into the list of UserDto usinf userToDto() and stream api.
		List<UserDto>usersDtoli =usersli.stream().map(u->this.userToDto(u)).collect(Collectors.toList());
	    return usersDtoli;
	}

	@Override
	public void deleteUser(int userId) 
	{
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
	}
	
	//below two methods are manual conversion of objects.
	//In first method,when we have to pass the User in save() method then we want User,not UserDto ,but we have UserDto.so we converting UserDto into User.
	//this method is for changing/converting the UserDto into the User
	/*
	 * public User dtoToUser(UserDto userdto) 
	 * { 
	 *  User user=new User();
	 *  user.setId(userdto.getId()); 
	 *  user.setName(userdto.getName());
	 *  user.setEmail(userdto.getEmail()); 
	 *  user.setPassword(userdto.getPassword());
	 *  user.setAbout(userdto.getAbout()); 
	 *  return user;
	 * 
	 * }
	 */
	
	//this method is for changing/converting the User into the UserDto
	/*
	 * public UserDto userToDto(User user) 
	 * { 
	 *  UserDto userDto=new UserDto();
	 *  userDto.setId(user.getId());
	 *  userDto.setName(user.getName());
	 *  userDto.setEmail(user.getEmail()); 
	 *  userDto.setPassword(user.getPassword());
	 *  userDto.setAbout(user.getAbout()); 
	 *  return userDto;
	 *   }
	 */
	
	
	//now we are converting UserDto into User using ModelMapper
	    public User dtoToUser(UserDto userdto) 
	    {
	    	User user=this.modelMapper.map(userdto, User.class);//map(source null):source=kontya object la convert kryche ahe.and second parameter= kontya class chya object mdhe convert krych ahe.
	    	 return user;                                        //we have to convert UserDto into User class.
	    }
	    
	  //now we are converting User into UserDto using ModelMapper
	    public UserDto userToDto(User user) 
	    {
	    	UserDto userDto=this.modelMapper.map(user, UserDto.class);//map(source null):source=kontya object la convert kryche ahe.and second parameter= kontya class chya object mdhe convert krych ahe.
	    	 return userDto;                                         //we have to convert User into UserDto class.
	    }
	    
		 
}
