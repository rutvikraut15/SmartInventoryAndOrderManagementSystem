package com.smartinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}