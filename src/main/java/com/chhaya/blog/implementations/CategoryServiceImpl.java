package com.chhaya.blog.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chhaya.blog.exceptions.ResourceNotFoundException;
import com.chhaya.blog.models.Category;
import com.chhaya.blog.models.User;
import com.chhaya.blog.payloads.CategoryDto;
import com.chhaya.blog.payloads.UserDto;
import com.chhaya.blog.repositories.CategoryRepo;
import com.chhaya.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepo catRepo;
    
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto)
	{
		//In UserServiceImp we have created separate methods for modelmapper.and this we direct apply logic here.
		Category cat=this.modelMapper.map(categoryDto,Category.class);//converting categoryDto to Category Entity.
		Category addedCat=this.catRepo.save(cat);//then here saving that Category data.
		return this.modelMapper.map(addedCat, CategoryDto.class);//coverting the Category into CategoryDto and returning the CategoryDto.
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId)
	{
		//here we finding the old category which already  existing and if not then throw error.
		Category cat=this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category Id", categoryId));
		//when we get the old category then now we set the new value to this category.
		cat.setCategoryTitle(categoryDto.getCategoryTitle());//tya old categoryla update krnyasathi new data "categoryDto" yat asel.
		cat.setCategorydescription(categoryDto.getCategorydescription());
		//now saving that new data
		Category updatedCat=this.catRepo.save(cat);
		//coverting the Category into CategoryDto and returning the CategoryDto.
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}
	
	@Override
	public List<CategoryDto> getAllCategory() 
	{
		List<Category> allCats=this.catRepo.findAll();
		//now we converting the all Category in the CategoriesDto one by one using stream api and model mapper.
		List<CategoryDto> allcatDto=allCats.stream().map(cat->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return allcatDto;
	}
	

	@Override
	public void deleteCategory(Integer catId)
	{
		Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id", catId));
		this.catRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategorybyId(Integer catId)
	{
		//here we finding that category using id.
		Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id", catId));
		//coverting that Category into CategoryDto and returning the CategoryDto.
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	
	
	
	

}
