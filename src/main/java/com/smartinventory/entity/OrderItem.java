package com.smartinventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(
            name = "order_item_seq",
            sequenceName = "ORDER_ITEM_SEQ",
            allocationSize = 1)
    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}