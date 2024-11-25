package com.developers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.model.Product;

public interface IProduct extends JpaRepository<Product, Integer>{

}
