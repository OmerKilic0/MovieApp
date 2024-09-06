package com.omer.sakila.movimo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omer.sakila.movimo.entity.Category;
import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.service.CategoryService;
import com.omer.sakila.movimo.service.CustomerService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	private CustomerService customerService;
	
	public CategoryController(CategoryService categoryService, CustomerService customerService) {
		this.categoryService = categoryService;
		this.customerService = customerService;
	}
	
	@GetMapping
	public String listCategories(ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/search")
	public String searchCategories(@RequestParam String name, ModelMap model) {
		Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
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
        Customer customer = customerService.authenticateUser();
		model.addAttribute("customer", customer);
        model.addAttribute("category", category);
        return "category-details";
    }
}
