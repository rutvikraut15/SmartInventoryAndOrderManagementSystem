package com.smartinventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.entity.Category;
import com.smartinventory.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@PostMapping
	public Category saveCategory(@Valid @RequestBody Category category) {
		
		return service.saveCategory(category);
	}
	
	
	@GetMapping
	public List<Category> getAllCategory(){
		
		return service.getAllCategory();
	}
	
	@GetMapping("/{id}")
	public Category findcategoryByID(@PathVariable Long id) {
		
		return service.findByID(id);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
		
		return service.updateCategoryByID(id, category);
	}
	
	@DeleteMapping("/{id}")
	public String removeCategory(@PathVariable Long id) {
		
		return service.deleteCategory(id);
	}
}
