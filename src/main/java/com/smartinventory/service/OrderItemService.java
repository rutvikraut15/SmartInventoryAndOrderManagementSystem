package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Order;
import com.smartinventory.entity.OrderItem;
import com.smartinventory.entity.Product;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.OrderItemRepository;
import com.smartinventory.repository.OrderRepository;
import com.smartinventory.repository.ProductRepository;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepository repository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;
	
	
	public OrderItem addItem(OrderItem item) {
		
		
		if (item.getOrder() == null || item.getOrder().getOrderId() == null) {
		    throw new ResourceNotFoundException("Order is required");
		}

		if (item.getProduct() == null || item.getProduct().getProductId() == null) {
		    throw new ResourceNotFoundException("Product is required");
		}
		
		
		Order order = orderRepository.findById(
	            item.getOrder().getOrderId())
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	   Product product = productRepository.findById(
	            item.getProduct().getProductId())
	            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

	    item.setOrder(order);
	    item.setProduct(product);

	    return repository.save(item);
	}
	
	public List<OrderItem> getAllItems(){
		
		return repository.findAll();
	}
	
	public OrderItem getItemByID(Long id){
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order-Item Not Found"));
	}
	
	public OrderItem updateItem(Long id, OrderItem item) {
		
		OrderItem order = repository.findById(id).orElse(null);
		
		if(order != null) {
			
			
			order.setPrice(item.getPrice());
			order.setQuantity(item.getQuantity());
			
			return repository.save(order);
			
		}
		
		return null;
	}
	
	public String removeOrderItem(Long id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			
			return "Orderd Item Is Deleted";
		}
		
		return "Item doesn't exist";
	}

}
