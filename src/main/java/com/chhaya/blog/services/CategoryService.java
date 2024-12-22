package com.chhaya.blog.services;

import java.util.List;

import com.chhaya.blog.payloads.CategoryDto;
import com.chhaya.blog.payloads.UserDto;

public interface CategoryService
{
	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
    //we dont need to write public keyword manually.because all methods in inteface are by default public/abstract.
	//update
	 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);//(new catogary data,old category id)
	
	//delete
	 void deleteCategory(Integer catId);
	
	//get
	 CategoryDto getCategorybyId(Integer catId);
	
	//getAll
	List<CategoryDto> getAllCategory();
	 
}
