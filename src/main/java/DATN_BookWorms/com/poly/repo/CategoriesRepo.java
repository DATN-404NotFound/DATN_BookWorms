package com.poly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entities.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, Integer>{

}
