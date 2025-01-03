package com.chhaya.blog.implementations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chhaya.blog.exceptions.ResourceNotFoundException;
import com.chhaya.blog.models.Post;
import com.chhaya.blog.models.Category;
import com.chhaya.blog.models.User;
import com.chhaya.blog.payloads.PostDto;
import com.chhaya.blog.payloads.PostResponse;
import com.chhaya.blog.repositories.PostRepo;
import com.chhaya.blog.repositories.UserRepo;
import com.chhaya.blog.repositories.CategoryRepo;
import com.chhaya.blog.services.PostService;

@Service
public class PostServiceImpl  implements PostService
{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Autowired
	private UserRepo userRepo;
	 
	
	@Autowired
	private CategoryRepo categoryRepo;
    
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) 
	{
		
		//here we finding the user using userRepo.
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id", userId));
	 	
		//here we finding the category using categoryRepo
		Category category=this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","category id", catId));
				
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);//set the user that we found above in this method
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId)
	{
		
		//finding that id's old data into post object from  db.
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId)
	{
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
		
		
	}

	//here we fetching all post
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) 
	{
		//Pagination logic
		  //Pageable p=PageRequest.of(pageNumber, pageSize);
	
		//by tornary operator
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		/*Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}*/
		  Pageable p=PageRequest.of(pageNumber,pageSize,sort);//here we passing object of sort-> Sort.by(sortBy)
		                                                             //Sort.by(sortBy).descending() ->direct as pn pass kru shkto.kinva separate varible use krun pass kru shkto.
		  Page<Post> pagePost=this.postRepo.findAll(p);
		  List<Post> allposts=pagePost.getContent();
		  
		//this line is for only get all post.
		//List<Post> allposts=this.postRepo.findAll();
		List<PostDto> allPostDtos=allposts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse  postResponse =new PostResponse();
		postResponse.setContent(allPostDtos);//pageResponse class mdhe content he list ahe mhnun ithe list pass keli.
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	//here we getting one post by id
	@Override
	public PostDto getPostById(Integer postId)
	{
		Post getpost=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		PostDto getpostDto=this.modelMapper.map(getpost,PostDto.class);
		return getpostDto;
	}

	//here we fethching all post related to the passed category.
	@Override
	public List<PostDto> getAllPostsByategory(Integer categoryid) 
	{
		//jichyashi related post search krycha ahet ithe cat mdhe ti categry milali 
		Category cat=this.categoryRepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryid));
		List<Post> catPostsli=this.postRepo.findByCategory(cat);//findByCategory(cat) is method from Postrepo interface.
		//converting all Post related to cat into PostDto.
		List<PostDto> catPostDtos=catPostsli.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return catPostDtos;
	}

	
	//here we fethching all post related to the passed user.
	@Override
	public List<PostDto> getPostByUser(Integer userid)
	{
		User user=this.userRepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("user", "user id", userid));
		List<Post> userPostli=this.postRepo.findByUser(user);
		List<PostDto> userPostDtos=userPostli.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return userPostDtos;
	}
    
	
	//seraching by title
	@Override
	public List<PostDto> searchPost(String keyword)
	{
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	

	

	

	
}
