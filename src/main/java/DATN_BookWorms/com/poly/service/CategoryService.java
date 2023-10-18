package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entities.Categories;

@Service
public interface CategoryService {
	List<Categories> findAll();
}
