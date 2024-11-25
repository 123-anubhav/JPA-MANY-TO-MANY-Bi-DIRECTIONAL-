package com.developers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue
	private Integer pId;
	private String pName;
	private Double pPrice;
	
	// @ManyToMany(mappedBy = "products")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "product_category", // Name of the join table
			joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "pId"), // Foreign key for Product
			inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "cId") // Foreign key for  Category																									
	)
	//@JsonManagedReference // Prevents infinite recursion
	//@JsonManagedReference
	private List<Category> categories;
}
