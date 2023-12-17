package com.poly.DATN_BookWorms.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.print.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.CategoryService;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.service.TypeBookService;
import com.poly.DATN_BookWorms.service.WriterService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/books")
public class BookRestController {
	@Autowired
	BookService bookService;


	@Autowired
	ShopService shopService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	PublishingCompanyService publishingCompanyService;
	@Autowired
	SessionService service;
	@Autowired
	TypeBookService typeBookService;

	@Autowired
	WriterService writerService;
	@GetMapping
	public List<Books> getAll() {
		return bookService.findAll();
	}
	@GetMapping("/ab")
	public List<Books> getAllById() {
		Account account = service.get("user");

		return bookService.findByshopidv2(account.getListOfShoponlines().get(0).getShopid());
	}


	@GetMapping("/cate/{id}")
	public List<Books> getAll5(@PathVariable("id") Integer id) {

		return  bookService.getBooksByCategoryID(id);
	}



	@GetMapping("/shop")
	public List<Books> getShop(@RequestParam("shopid") Integer shopid) {

		return  bookService.findByShopList(shopid);
	}






	@PutMapping("{id}")
	public Books update(@PathVariable("id") Integer id, @RequestBody Books book) {
		return bookService.update(book);
	}

	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		bookService.delete(bookId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public Books getOne(@PathVariable("id") Long id) {
		System.out.println("111111111111111" + bookService.findById(id));
		return bookService.findById(id);
	}
	
	@GetMapping("/names")
	public List<Categories> getAllCategoryNames() {
		return categoryService.findAll();
	}

	@GetMapping("/publishingcompany")
	public ResponseEntity<List<Publishingcompanies>> getAllPublishingCompany() {
		List<Publishingcompanies> publishingCompanies = publishingCompanyService.findAll();

		if (publishingCompanies != null && !publishingCompanies.isEmpty()) {
			return new ResponseEntity<>(publishingCompanies, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/writers")
	public ResponseEntity<List<Writtingmasters>> getAllWriter() {
		List<Writtingmasters> writers = writerService.findAll();

		if (writers != null && !writers.isEmpty()) {
			return new ResponseEntity<>(writers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/createBook")
	public ResponseEntity<Books> createBook(@RequestBody @Valid Books book) {
		Account user = service.get("user");
		Shoponlines shoponlines = shopService.findUserId(user.getUserid());

		book.setShopid(shoponlines.getShopid());
		Books newBook = bookService.save(book);
		return ResponseEntity.ok(newBook);
	}

	@PostMapping(value = "/createTypeBook")
	public ResponseEntity<Typebooks> createTypeBooks(@RequestBody @Valid Typebooks typebooks) {

		Typebooks typebook = typeBookService.save(typebooks);
		return ResponseEntity.ok(typebook);
	}

	@PostMapping(value = "/saveBook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveProfileChange(@RequestParam(value = "fileImage") Optional<MultipartFile> multipartFile, @RequestParam("book") Book book, @RequestParam("company") Publishingcompanies publishingcompanies, @RequestParam("categories") List<Categories> categories) throws Exception {


	}

	@PutMapping("/updateIsActive/{bookId}")
	public ResponseEntity<Void> updateIsActive(@PathVariable Long bookId, @RequestBody Map<String, Boolean> requestBody) {
		boolean newIsActive = requestBody.get("isactive");
		bookService.updateIsActive(bookId, newIsActive);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/delete/{bookId}")
	public ResponseEntity<Void> deleteBooks(@PathVariable Long bookId, @RequestBody Map<String, Boolean> requestBody) {
		boolean newIsActive = requestBody.get("isactive");
		bookService.deleteBook(bookId, newIsActive);
		return ResponseEntity.ok().build();
	}
}
