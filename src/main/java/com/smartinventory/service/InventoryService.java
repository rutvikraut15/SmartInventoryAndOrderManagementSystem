package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Inventory;
import com.smartinventory.entity.Product;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.InventoryRepository;
import com.smartinventory.repository.ProductRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public Inventory addInventory(Inventory inventory) {
    	
    	if (inventory.getProduct() == null || inventory.getProduct().getProductId() == null) {
    	    throw new ResourceNotFoundException("Product is required");
    	}

        Product product = productRepository.findById(
                inventory.getProduct().getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        inventory.setProduct(product);

        return repository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return repository.findAll();
    }

    public Inventory getInventoryById(Long id) {
    	
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory Not Found"));
    }

    public Inventory updateInventory(Long id, Inventory inventory) {

        Inventory inv = repository.findById(id).orElse(null);

        if (inv != null) {

            inv.setStockQuantity(inventory.getStockQuantity());
            inv.setReorderLevel(inventory.getReorderLevel());
            inv.setLastUpdated(inventory.getLastUpdated());

            return repository.save(inv);
        }

        return null;
    }

    public String deleteInventory(Long id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

            return "Inventory Deleted";
        }

        return "Inventory Not Found";
    }
}