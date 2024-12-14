package com.chhaya.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Dto:Data transfer object
//this class is used to transfer the user data.means we are not sending the user data direct;y from service class.we send user data to serviceclass using this class.
//here we use lombok for creating constructor,getter,setter
@NoArgsConstructor
@Getter
@Setter
public class UserDto 
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
