package com.smartinventory.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name="CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	@SequenceGenerator(
	        name = "category_seq",
	        sequenceName = "CATEGORY_SEQ",
	        allocationSize = 1)
	
	@Column(name ="CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "CATEGORY_NAME", nullable = false, length = 100)
	private String categoryName;
	
	
	@Column(name = "DESCRIPTION", length = 255)
	private String description;
	
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
	
	
}
