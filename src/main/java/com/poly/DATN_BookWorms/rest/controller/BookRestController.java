package com.poly.DATN_BookWorms.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Shoponlines;
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
	
	@GetMapping("/cate/{id}")
	public List<Books> getAll5(@PathVariable("id") Integer id) {
		
		return  bookService.getBooksByCategoryID(id);
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
	public void delete(@PathVariable("id")Long id) {
		bookService.delete(id);
	}
	
	@GetMapping("/{id}")
	public Books getOne(@PathVariable("id")Long id) {
		System.out.println("111111111111111"+ bookService.findById(id));
		return bookService.findById(id);
	}
	
	@GetMapping("/type")
	public List<Integer> getBookWithTypeBook(@RequestParam("listtype") String listtype){ 
		String[] listty =listtype.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for(int i=0; i<listty.length;i++) { 
			listtypes.add(Integer.parseInt(listty[i]));
		}
		
		return bookService.getBookWithTypeBook(listtypes);
	}
	
	@GetMapping("/writer")
	public List<Integer> getBookWithWriter(@RequestParam("listwriter") String listwriter){ 
		String[] listty =listwriter.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for(int i=0; i<listty.length;i++) { 
			listtypes.add(Integer.parseInt(listty[i]));
		}
		
		return bookService.getBookWithWriters(listtypes);
	}
	
	@GetMapping("/Eva")
	public List<Integer> getBookWithEvaluate(@RequestParam("listeva") String listeva){ 
		String[] listty =listeva.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for(int i=0; i<listty.length;i++) { 
			listtypes.add(Integer.parseInt(listty[i]));
		}
		return bookService.getBookWithEvaluate(listtypes);
	}
//	@GetMapping("/list/{id}")
//	public List<Shoponlines> listshopDeal(@PathVariable("id") Long id){ 
//		System.out.println("listshopBooks "+ bookService.list_shopId_deal(id));
//		return bookService.list_shopId_deal(id);
//	}
}
