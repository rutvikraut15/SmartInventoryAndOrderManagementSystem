package com.smartinventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	

	
	List<Product> findByCategoryCategoryId(Long categoryId);
	
	 List<Product> findBySupplierSupplierId(Long supplierId);
	 
	 List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	 
	 List<Product> findByProductNameContainingIgnoreCase(String productName);
	 
	 List<Product> findByCategoryCategoryNameIgnoreCase(String categoryName);
	 
	 List<Product> findByCategoryCategoryNameIgnoreCaseAndSupplierSupplierNameIgnoreCase(
		        String categoryName,
		        String supplierName);
	 
	 @Query("SELECT p FROM Product p WHERE p.price > :price")
	 List<Product> findProductsGreaterThan(@Param("price") Double price);
	 
	 
}
