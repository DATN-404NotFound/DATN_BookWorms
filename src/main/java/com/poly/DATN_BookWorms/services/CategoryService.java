package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
	List<Category> findAll();

}
