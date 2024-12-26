package com.chhaya.blog.services;

import java.util.List;

import com.chhaya.blog.models.Post;
import com.chhaya.blog.payloads.PostDto;
import com.chhaya.blog.payloads.PostResponse;

public interface PostService
{
     //Cretae
	PostDto createPost(PostDto postDto,Integer userId ,Integer catId);
	
	//Update
	PostDto updatePost(PostDto postdto,Integer postId);
	
	//Delete
	void deletePost(Integer postId);
	
	//get  all post
	//List<PostDto>getAllPost(Integer pageNumber,Integer pageSize );
	//get post by all details
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by Category
	List<PostDto> getAllPostsByategory(Integer categoryid);
	
	//get all post by User
	List<PostDto> getPostByUser(Integer userid);
	
	//get post by searching keyword
	List<PostDto> searchPost(String keyword);
	
}
