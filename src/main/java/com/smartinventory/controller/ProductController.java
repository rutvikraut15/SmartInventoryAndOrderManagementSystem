package com.smartinventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.dto.ProductDTO;
import com.smartinventory.entity.Product;
import com.smartinventory.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public Product saveProduct(@Valid @RequestBody Product product) {
		
		return service.addProduct(product);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public List<ProductDTO> getAllProduct(){
		
		return service.getAllProductsDTO();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/{id}")
	public Product findProductByID(@PathVariable Long id) {
		
		return service.findProduct(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		
		return service.updateProduct(id, product);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public String removeProduct(@PathVariable Long id) {
		
	
		return service.deleteById(id);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/page")
	public Page<Product> getProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    return service.getProducts(page, size);
	}
	
	@GetMapping("/sort")
	public List<Product> sortProducts(
	        @RequestParam String field,
	        @RequestParam(defaultValue = "asc") String direction) {

	    return service.sortProducts(field, direction);
	}
	
	
	@GetMapping("/search")
	public List<ProductDTO> searchProduct(@RequestParam String name) {

	    return service.searchProduct(name);

	}
	
	@GetMapping("/category/{id}")
	public List<Product> getProductsByCategory(@PathVariable Long id) {

	    return service.getProductsByCategory(id);

	}
	
	@GetMapping("/supplier/{id}")
	public List<Product> getProductsBySupplier(@PathVariable Long id) {

	    return service.getProductsBySupplier(id);

	}
	
	@GetMapping("/price")
	public List<Product> getProductsByPrice(
	        @RequestParam Double minPrice,
	        @RequestParam Double maxPrice) {

	    return service.getProductsByPrice(minPrice, maxPrice);

	}
	
	@GetMapping("/category/{categoryName}")
	public List<ProductDTO> getProductsByCategory(
	        @PathVariable String categoryName) {

	    return service.getProductsByCategory(categoryName);
	}
	
	@GetMapping("/filter")
	public List<ProductDTO> filterProducts(
	        @RequestParam String category,
	        @RequestParam String supplier) {

	    return service.filterProducts(category, supplier);
	}
	
	@GetMapping("/greater")
	public List<ProductDTO> getProductsGreaterThan(
	        @RequestParam Double price){

	    return service.getProductsGreaterThan(price);
	}
	
}
