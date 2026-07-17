package com.smartinventory.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartinventory.dto.ProductDTO;
import com.smartinventory.entity.Category;
import com.smartinventory.entity.Product;
import com.smartinventory.entity.Supplier;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CategoryRepository;
import com.smartinventory.repository.ProductRepository;
import com.smartinventory.repository.SupplierRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private static final Logger logger =
	        LoggerFactory.getLogger(ProductService.class);
	
	public List<Product> viewAll(){
		
		return repository.findAll(Sort.by("productId"));
	}
	
	
	public Product addProduct(Product product) {
		
		logger.info("Adding product: {}", product.getProductName());
		
		if (product.getCategory() == null) {
	        throw new ResourceNotFoundException("Category is required");
	    }

	    // Check if supplier is present
	    if (product.getSupplier() == null) {
	        throw new ResourceNotFoundException("Supplier is required");
	    }
		
		
		 Category category = categoryRepository.findById(
		            product.getCategory().getCategoryId())
		            .orElseThrow(() -> new RuntimeException("Category not found"));

		    Supplier supplier = supplierRepository.findById(
		            product.getSupplier().getSupplierId())
		            .orElseThrow(() -> new RuntimeException("Supplier not found"));

		    product.setCategory(category);
		    product.setSupplier(supplier);
		
		return repository.save(product);
	}
	
	public Product findProduct(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}
	

	public Product updateProduct(Long id, Product product) {
		
		Product p = repository.findById(id).orElse(null);
		
		if(p != null) {
			
			p.setProductName(product.getProductName());
			p.setPrice(product.getPrice());
			p.setDescription(product.getDescription());
			p.setQuantity(product.getQuantity());
			
		 return repository.save(p);
		}
		
		return null;
	}
	
	
	
	public String deleteById(Long id) {
		
		logger.info("Deleting product with ID: {}", id);
		
		if(repository.existsById(id)) {
			
			repository.deleteById(id);
			
			return "Product Deleted";
		}
		
		return "Product Not Found";
		
	}
	
	public Page<Product> getProducts(int page, int size) {
	    return repository.findAll(PageRequest.of(page, size));
	}
	
	
	public List<Product> sortProducts(String field, String direction) {

	    Sort sort = direction.equalsIgnoreCase("desc")
	            ? Sort.by(field).descending()
	            : Sort.by(field).ascending();

	    return repository.findAll(sort);
	}
	
	public List<ProductDTO> searchProduct(String name) {

	    return repository.findByProductNameContainingIgnoreCase(name)
	            .stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	
	
	public List<Product> getProductsByCategory(Long categoryId) {

	    return repository.findByCategoryCategoryId(categoryId);

	}
	
	
	public List<Product> getProductsBySupplier(Long supplierId) {

	    return repository.findBySupplierSupplierId(supplierId);

	}
	
	
	public List<Product> getProductsByPrice(Double minPrice, Double maxPrice) {

	    return repository.findByPriceBetween(minPrice, maxPrice);

	}
	
	
	public ProductDTO convertToDTO(Product product) {

		 ProductDTO dto = modelMapper.map(product, ProductDTO.class);

		    dto.setCategoryName(product.getCategory().getCategoryName());
		    dto.setSupplierName(product.getSupplier().getSupplierName());
		    
		   
		    return dto;

	}
	
	public List<ProductDTO> getAllProductsDTO() {

	    List<Product> products = repository.findAll(Sort.by("productId"));

	    return products.stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	
	
	public List<ProductDTO> getProductsByCategory(String categoryName) {

	    return repository.findByCategoryCategoryNameIgnoreCase(categoryName)
	            .stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	
	
	public List<ProductDTO> filterProducts(String category, String supplier) {

	    return repository
	            .findByCategoryCategoryNameIgnoreCaseAndSupplierSupplierNameIgnoreCase(
	                    category, supplier)
	            .stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	
	public List<ProductDTO> getProductsGreaterThan(Double price){

	    return repository.findProductsGreaterThan(price)
	            .stream()
	            .map(this::convertToDTO)
	            .toList();
	}
	
}
