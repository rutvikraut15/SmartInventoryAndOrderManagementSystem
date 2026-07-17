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
@Setter
@Getter

@Table(name="SUPPLIER")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
	@SequenceGenerator(
			 name="supplier_seq",
	          sequenceName="SUPPLIER_SEQ",
	          allocationSize=1
			)
	@Column(name ="SUPPLIER_ID")
	private Long supplierId;
	
	@Column(name="SUPPLIER_NAME", nullable=false)
	private String supplierName;

	@Column(name="EMAIL")
	private String email;

	@Column(name="PHONE")
	private String phone;

	@Column(name="ADDRESS")
	private String address;
	
	
	@OneToMany(mappedBy = "supplier")
	
	private List<Product> products;
	
}
