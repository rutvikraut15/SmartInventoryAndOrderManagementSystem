package com.smartinventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(
            name = "customer_seq",
            sequenceName = "CUSTOMER_SEQ",
            allocationSize = 1)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "NAME", nullable = false)
    private String customerName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ADDRESS")
    private String address;
}
