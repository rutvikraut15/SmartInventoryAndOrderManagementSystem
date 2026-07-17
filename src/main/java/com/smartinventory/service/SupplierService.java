package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Supplier;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.SupplierRepository;

@Service
public class SupplierService {


	@Autowired
	SupplierRepository repository;
	
	
	public List<Supplier> viewAll(){
		
	  return repository.findAll();
	}
	
	public Supplier addSupllier(Supplier supplier) {
		
		return repository.save(supplier);
	}
	
	public Supplier findSupplier(Long id){
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier Not Found"));
	}
	
	public Supplier updateSupplier(Long id, Supplier supplier) {
		
		Supplier s = repository.findById(id).orElse(null);
		
		if(s != null) {
			
			s.setSupplierName(supplier.getSupplierName());
			s.setEmail(supplier.getEmail());
			s.setPhone(supplier.getPhone());
			s.setAddress(supplier.getAddress());
			
			return repository.save(s);
		}
	
		return null;
	}
	
	
	public String removeSuppllier(Long id) {
		
		if(repository.existsById(id)) {
			
			repository.deleteById(id);
			
			return "Supplier deleted";
		}
		
		return "Supplier doesn't exist";
	}
	
	
	
	
	
}
