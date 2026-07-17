package com.smartinventory.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(
            name = "order_seq",
            sequenceName = "ORDER_SEQ",
            allocationSize = 1)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}