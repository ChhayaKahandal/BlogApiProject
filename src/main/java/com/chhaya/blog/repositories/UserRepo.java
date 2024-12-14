package com.chhaya.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chhaya.blog.models.User;

public interface UserRepo extends JpaRepository<User, Integer>//JpaRepository<T, ID>: t=On which entity do you want to work."User entity",ID=data type of Id in user entity."Integer"
{

}
