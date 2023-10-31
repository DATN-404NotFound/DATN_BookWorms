package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.service.BookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/books")
public class BookRestController {
	@Autowired
	BookService bookService;
	
	@GetMapping
	public List<Books> getAll() {
		return bookService.findAll();
	}
	
	@PostMapping
	public Books create(@RequestBody Books book) {
		return bookService.create(book);
	}
	
	@PutMapping("{id}")
	public Books update(@PathVariable("id")Integer id, @RequestBody Books book) {
		return bookService.update(book);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id")int id) {
		bookService.delete(id);
	}
	
	@GetMapping("/{id}")
	public Books getOne(@PathVariable("id")int id) {
		System.out.println("111111111111111"+ bookService.findById(id));
		return bookService.findById(id);
	}
}
