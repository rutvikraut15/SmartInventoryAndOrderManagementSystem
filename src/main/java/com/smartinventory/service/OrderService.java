package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Customer;
import com.smartinventory.entity.Order;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CustomerRepository;
import com.smartinventory.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
    	
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
    }

    public Order saveOrder(Order order) {

    	
    	if (order.getCustomer() == null || order.getCustomer().getCustomerId() == null) {
    	    throw new ResourceNotFoundException("Customer is required");
    	}
    	
    	
        Customer customer = customerRepository.findById(
                order.getCustomer().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {

        Order existing = orderRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setOrderDate(order.getOrderDate());
            existing.setTotalAmount(order.getTotalAmount());
            existing.setStatus(order.getStatus());

            return orderRepository.save(existing);
        }

        return null;
    }

    public String deleteOrder(Long id) {

        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "Order Deleted Successfully";
        }

        return "Order Not Found";
    }
}