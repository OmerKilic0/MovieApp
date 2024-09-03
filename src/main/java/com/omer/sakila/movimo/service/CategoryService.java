package com.omer.sakila.movimo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Category;
import com.omer.sakila.movimo.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public List<Category> searchCategoriesByName(String name){
		return categoryRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id);
	}
	
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
}
