package com.chhaya.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chhaya.blog.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>
{

}
