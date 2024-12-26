package com.chhaya.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chhaya.blog.models.Category;
import com.chhaya.blog.models.Post;
import com.chhaya.blog.models.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
	
	
	//this two are custom methods
	
	//we want some user's all post
	List<Post> findByUser(User user);
	
	//we want some category's all post
	List<Post> findByCategory(Category category);
	
	//for searching post by title
	//this method will automatically write "Like" query for us.
	List<Post> findByTitleContaining(String title);
}
