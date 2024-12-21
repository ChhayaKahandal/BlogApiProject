package com.chhaya.blog.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")//here we changing table name as users
//here we using "lombok" for generte constructor,getter and setters.
@NoArgsConstructor
@Getter
@Setter
public class User
{
	
	@Id//id set as primary key.
    @GeneratedValue(strategy=GenerationType.AUTO)//id will be autogenerated.
     private int id;
    
    @Column(name="user_name",nullable=false,length=100)//nullable=false  null value not allowed.lenghth of name is 100.
     private String name;
     private String email;
     private String password;
     private String about;
	
	
	 
         
         
         
         
}
