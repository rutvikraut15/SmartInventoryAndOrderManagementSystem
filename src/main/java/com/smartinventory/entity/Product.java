package com.smartinventory.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	
	@SequenceGenerator(
			name = "product_seq",
            sequenceName = "PRODUCT_SEQ",
            allocationSize = 1)
	@Column(name="PRODUCT_ID")
	private Long productId;
	
	@Column(name = "PRODUCT_NAME", nullable = false, length = 100)
	@NotBlank(message = "Product name is required")
	private String productName;

	@Column(name = "PRICE")
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than 0")
	private Double price;

	@Column(name = "QUANTITY")
	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;

	@Column(name = "DESCRIPTION")
	@NotBlank(message = "Description is required")
	private String description;

	
    @ManyToOne
    @JoinColumn(name ="CATEGORY_ID")
    @JsonIgnoreProperties("products")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="SUPPLIER_ID")
    @JsonIgnoreProperties("products")
    private Supplier supplier;

    
}
