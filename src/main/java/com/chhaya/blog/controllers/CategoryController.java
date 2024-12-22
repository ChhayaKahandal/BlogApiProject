package com.chhaya.blog.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chhaya.blog.implementations.CategoryServiceImpl;
import com.chhaya.blog.payloads.ApiResponse;
import com.chhaya.blog.payloads.CategoryDto;
import com.chhaya.blog.payloads.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
	@Autowired
	private CategoryServiceImpl catservice;
	
	//create
	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody  CategoryDto catDto)
	{
		CategoryDto createdCat=this.catservice.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/updateCategory/{catId}")
	public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody  CategoryDto catDto,@PathVariable("catId") Integer cId)
	{
		CategoryDto updatedCat=this.catservice.updateCategory(catDto, cId);
		return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
	}
	
	//getAll
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAllCat()
		{
			/*List<CategoryDto> getCats=this.catservice.getAllCategory();
			return new ResponseEntity<List<CategoryDto>>(getCats,HttpStatus.OK);*/
			return ResponseEntity.ok(this.catservice.getAllCategory());
		}

	//delete
	@DeleteMapping("/deleteCategory/{catId}")
	public ResponseEntity<ApiResponse> deleteCat(@PathVariable("catId") Integer cId)
	{
		this.catservice.deleteCategory(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category succesfully deleted",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/getCategory/{catId}")
	public ResponseEntity<CategoryDto> getCatbyId(@PathVariable("catId") Integer cId)
	{
		CategoryDto catbyId=this.catservice.getCategorybyId(cId);
		return new ResponseEntity<CategoryDto>(catbyId,HttpStatus.OK);
	}
	
	
	
	
}
