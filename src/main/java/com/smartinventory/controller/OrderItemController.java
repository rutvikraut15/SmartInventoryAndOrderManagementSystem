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

import com.smartinventory.entity.OrderItem;
import com.smartinventory.service.OrderItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @PostMapping
    public OrderItem save(@RequestBody OrderItem item) {
        return service.addItem(item);
    }

    @GetMapping
    public List<OrderItem> getAll() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public OrderItem getById(@Valid @PathVariable Long id) {
        return service.getItemByID(id);
    }

    @PutMapping("/{id}")
    public OrderItem update(@PathVariable Long id,
                            @RequestBody OrderItem item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return service.removeOrderItem(id);
    }
}