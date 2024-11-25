package com.developers.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue
	private Integer cId;
	private String cName;

	@ManyToMany(mappedBy = "categories")
	@JsonIgnore // Use @JsonIgnore to prevent recursion instead of @JsonBackReference
	private List<Product> products;

}
