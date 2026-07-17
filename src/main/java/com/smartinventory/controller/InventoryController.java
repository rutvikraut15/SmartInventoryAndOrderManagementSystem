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

import com.smartinventory.entity.Inventory;
import com.smartinventory.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping
    public Inventory save(@Valid @RequestBody Inventory inventory) {
        return service.addInventory(inventory);
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable Long id) {
        return service.getInventoryById(id);
    }

    @PutMapping("/{id}")
    public Inventory update(@PathVariable Long id,
                            @RequestBody Inventory inventory) {
        return service.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.deleteInventory(id);
    }
}