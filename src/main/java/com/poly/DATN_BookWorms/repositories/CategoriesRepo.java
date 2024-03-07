package com.poly.DATN_BookWorms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.DATN_BookWorms.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Category, Integer>{

    @Query("SELECT o FROM Category o WHERE o.categoryid in :categoryIds")
    List<Category> findByInId(@Param("categoryIds") List<Integer> categoryIds);
}
