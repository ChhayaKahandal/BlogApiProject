package com.chhaya.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse 
{
	
	private List<PostDto>content;
	private int pageNumber;
	private int pageSize;
	//total elements 
	private long totalElements;
	private int totalPages;
	// checking the current page is last page
	private boolean lastPage;
	
	
	
	

}
