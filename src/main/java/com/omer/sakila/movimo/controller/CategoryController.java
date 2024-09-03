package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Category;
import com.omer.sakila.movimo.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public String listCategories(ModelMap model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/search")
	public String searchCategories(@RequestParam String name, ModelMap model) {
		if (name != null && !name.isEmpty()) {
            model.addAttribute("categories", categoryService.searchCategoriesByName(name));
        } else {
            model.addAttribute("categories", categoryService.getAllCategories());
        }
        return "categories";
	}
	
	@GetMapping("/{name}")
	public String getCategoryByName(@PathVariable String name, ModelMap model) {
        Category category = categoryService.getCategoryByName(name);
        model.addAttribute("category", category);
        return "category-details";
    }
}
