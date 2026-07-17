package com.smartinventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartinventory.entity.Supplier;
import com.smartinventory.service.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	SupplierService service;
	
	
	@PostMapping
	public Supplier joinSupplier(@Valid  @RequestBody Supplier supplier) {
		
		return service.addSupllier(supplier);
	}
	
	@GetMapping
	public List<Supplier> getAll(){
		
		return service.viewAll();
	}
	
	@GetMapping("/{id}")
	public Supplier getSupplier(@PathVariable Long id){
		
		return service.findSupplier(id);
	}
	
	@PostMapping("/{id}")
	public Supplier updateSupplier(@PathVariable Long id,@RequestBody Supplier supplier) {
		
		return service.updateSupplier(id, supplier);
	}
	
	@DeleteMapping("/{id}")
	public String deleteSupplier(@PathVariable Long id) {
		
		return service.removeSuppllier(id);
	}
	
	
}
