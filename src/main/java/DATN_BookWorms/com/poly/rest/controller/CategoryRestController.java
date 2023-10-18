package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.service.CategoryService;
import com.poly.entities.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping()
	public List<Categories> getAll() {
		return categoryService.findAll();
	}
	
}
