package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Categories;

@Service
public interface CategoryService {
	List<Categories> findAll();
}
