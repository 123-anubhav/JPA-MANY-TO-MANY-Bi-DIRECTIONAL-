package com.developers.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.model.Category;

public interface ICategory extends JpaRepository<Category, Integer> {
   // Optional<Category> findBycName(String cName);
}
