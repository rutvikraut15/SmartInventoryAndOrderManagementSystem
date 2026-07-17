package com.smartinventory.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INVENTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(
            name = "inventory_seq",
            sequenceName = "INVENTORY_SEQ",
            allocationSize = 1)
    @Column(name = "INVENTORY_ID")
    private Long inventoryId;
    

    @Column(name = "STOCK_QUANTITY")
    private Integer stockQuantity;

    @Column(name = "REORDER_LEVEL")
    private Integer reorderLevel;

    @Column(name = "LAST_UPDATED")
    private LocalDate lastUpdated;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}