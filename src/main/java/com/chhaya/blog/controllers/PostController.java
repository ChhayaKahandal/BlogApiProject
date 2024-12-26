package com.chhaya.blog.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chhaya.blog.config.AppContstantsValue;
import com.chhaya.blog.implementations.PostServiceImpl;
import com.chhaya.blog.models.Post;
import com.chhaya.blog.payloads.ApiResponse;
import com.chhaya.blog.payloads.PostDto;
import com.chhaya.blog.payloads.PostResponse;
import com.chhaya.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController 
{
  @Autowired
  private PostService postService;
  
  
  //create
  //http://localhost:9090/api/posts/user/1/category/4/posts->means 1no cha user 4no chya categpry mdhe post create krtoy.
  /*
   *http://localhost:9090/api/posts/page?pageNumber=0&pageSize=5&sortBy=title
   */
  @PostMapping("/user/{userId}/category/{categoryId}/posts")//konta user ha kontya category chya under post create krtoy.
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") Integer uId,@PathVariable("categoryId") Integer cId)
  {
	  PostDto createdPost=this.postService.createPost(postDto, uId, cId);
	  return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
  }
  
  
  //get post by category
  @GetMapping("/category/{catId}/posts")//kontya category shi related post havya ahet.
  public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("catId") Integer cId)
  {
	  List<PostDto> posts=this.postService.getAllPostsByategory(cId);
	  return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
  }
  
  
  //get post by user
  @GetMapping("/user/{userId}/posts")//kontya user ne create kelelya post havya ahet.
  public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Integer uId)
  {
	  List<PostDto> posts=this.postService.getPostByUser(uId);
	  return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
  }
  
  
  //get all post
  // http://localhost:9090/api/posts/page?pageNumber=1&pageSize=5
  @GetMapping("/page")
  public ResponseEntity<PostResponse> getAllPost(
		         @RequestParam(value="pageNumber",defaultValue=AppContstantsValue.PAGE_NUMBER,required=false) Integer pageNo,
		         @RequestParam(value="pageSize",defaultValue=AppContstantsValue.PAGE_SIZE,required=false) Integer pageSize,
		         @RequestParam(value="sortBy",defaultValue=AppContstantsValue.SORT_BY ,required=false) String sortBy,
		         @RequestParam(value="sortDirection",defaultValue=AppContstantsValue.SORT_DIRECTION,required=false) String sortDirection)
  //defaultValue="0" ->first we write harcoded values like this.now for best practice we can use this values using class.
  //@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNo,->this "pageNumber" is pass in url,jar apan value dili nhi tr to default value 0 gheil,ani reuired is false means url mdhe parameter dya kinva nka deu.
  {
	  PostResponse postResponse =this.postService.getAllPost(pageNo, pageSize,sortBy,sortDirection);
	  //List<PostDto> posts=this.postService.getAllPost(pageNo,pageSize);
	 // List<PostDto> posts=this.postService.getAllPost();
	  return new ResponseEntity<PostResponse> (postResponse,HttpStatus.OK);
  }
  
  
  //get post by user
  @GetMapping("/{postId}")
  public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer pId)
  {
	  PostDto post=this.postService.getPostById(pId);
	  return new ResponseEntity<PostDto>(post,HttpStatus.OK);
  }
  
  
  //delet post by id
  @DeleteMapping("/{postId}")
  public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") Integer pId)
  {
	  
	     this.postService.deletePost(pId);
	    //return new  ResponseEntity(Map.of("message","User deleted Successfully"),HttpStatus.OK);//we can use this way also without ApiResponse class
		return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
  }
  
  //update post
  @PutMapping("/{postId}")
  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto, @PathVariable("postId") Integer pId)
  {
	  PostDto updatepost=this.postService.updatePost(postdto, pId);
	  return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
  }
  
  //search by title name
  @GetMapping("/search/{keyword}")
  public ResponseEntity<List<PostDto>> searchPostByTitlle(@PathVariable("keyword") String keword)
  {
	 List<PostDto> result=this.postService.searchPost(keword);
	  return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK) ;
  }
  
 
  


  
}
