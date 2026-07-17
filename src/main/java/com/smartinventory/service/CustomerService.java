package com.smartinventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartinventory.entity.Customer;
import com.smartinventory.exception.ResourceNotFoundException;
import com.smartinventory.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> viewAll() {
        return repository.findAll();
    }

    public Customer findCustomer(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
    }

    public Customer updateCustomer(Long id, Customer customer) {

        Customer c = repository.findById(id).orElse(null);

        if(c != null) {
            c.setCustomerName(customer.getCustomerName());
            c.setEmail(customer.getEmail());
            c.setPhone(customer.getPhone());
            c.setAddress(customer.getAddress());

            return repository.save(c);
        }

        return null;
    }

    public String deleteCustomer(Long id) {

        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Customer Deleted";
        }

        return "Customer Not Found";
    }
}