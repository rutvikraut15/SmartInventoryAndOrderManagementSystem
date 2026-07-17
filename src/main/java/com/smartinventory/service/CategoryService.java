package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Category;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CategoryRepository;

@Service
public class CategoryService {

	
	@Autowired
	CategoryRepository repository;
	
	
	 public Category saveCategory( Category category) {
	
		 return repository.save(category);
	}
	 
	 public List<Category> getAllCategory() {
		 
		 return repository.findAll();
		
	}
	 
	 public Category findByID(Long id) {
		 
		 return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
	 }
	 
	 public Category updateCategoryByID(Long id, Category category) {
		 
		 Category existing = repository.findById(id).orElse(null);
		 
		 if(existing != null) {
			 
			 existing.setCategoryName(category.getCategoryName());
			 existing.setDescription(category.getDescription());
			 
			 
			 return repository.save(existing);
			 
		 }
		 
		 return null;
	 }
	 
	 public String deleteCategory(Long id) {
		 
		 if(repository.existsById(id)) {
			 repository.deleteById(id);
			 
			 return "category deleted";
		 }
		 
		 return "Category not found";
	 }
	 
	 

}
