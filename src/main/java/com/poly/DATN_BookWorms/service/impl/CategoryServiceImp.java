package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Categories;
import com.poly.DATN_BookWorms.repo.CategoriesRepo;
import com.poly.DATN_BookWorms.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService{

	@Autowired
	CategoriesRepo cateRepo;
	
	@Override
	public List<Categories> findAll() {
		// TODO Auto-generated method stub
		return cateRepo.findAll();
	}
	
	
}
