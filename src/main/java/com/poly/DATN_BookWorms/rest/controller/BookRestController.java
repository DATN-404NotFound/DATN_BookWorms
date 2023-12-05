package com.poly.DATN_BookWorms.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.service.CategoryService;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;
import com.poly.DATN_BookWorms.service.TypeBookService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.service.BookService;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/books")
public class BookRestController {
	@Autowired
	BookService bookService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	PublishingCompanyService publishingCompanyService;
	@Autowired
	SessionService service;
	@Autowired
	TypeBookService typeBookService;
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
	

	
	@PutMapping("{id}")
	public Books update(@PathVariable("id")Integer id, @RequestBody Books book) {
		return bookService.update(book);
	}

	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
		bookService.delete(bookId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public Books getOne(@PathVariable("id")Long id) {
		System.out.println("111111111111111"+ bookService.findById(id));
		return bookService.findById(id);
	}
	
//	@GetMapping("/list/{id}")
//	public List<Shoponlines> listshopDeal(@PathVariable("id") Long id){ 
//		System.out.println("listshopBooks "+ bookService.list_shopId_deal(id));
//		return bookService.list_shopId_deal(id);
//	}
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
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Books> createBooks(@RequestBody Books books) {
		Books createBook = bookService.creates(books);
		return ResponseEntity.ok(createBook);
	}
	@PostMapping(value = "/createtypebooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Typebooks> createTypebooks(@RequestParam("books") Books books,@RequestParam("Categories") Categories categories ) {
		Typebooks createTypeBook = typeBookService.create(books, categories);
		return ResponseEntity.ok(createTypeBook);

	}
	@PostMapping(value = "/saveimagebook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveProfileChange(@RequestParam(value = "fileImage") Optional<MultipartFile> multipartFile, @RequestParam("shopId") String shopId) throws Exception {
//		if (multipartFile.isEmpty()) {
//			Shoponlines shoponlines = shopService.findById(Integer.parseInt(shopId));
//			shopService.save(shoponlines);
//		} else {
//			MultipartFile file = multipartFile.get();
//			String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//			String uploadDir = "D:/DATN/DATN_BookWorms/src/main/resources/static/SellerChannel/images/";
//			Path uploadPath = Paths.get(uploadDir);
//			if (!java.nio.file.Files.exists(uploadPath)) {
//				java.nio.file.Files.createDirectories(uploadPath);
//			}
//			try {
//				InputStream inputStream = file.getInputStream();
//				Path filePath = uploadPath.resolve(fileName);
//				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//				//save change profile
//				Shoponlines shoponlines = shopService.findById(Integer.parseInt(shopId));
////                shoponlines.setLogo(fileName);
//				shopService.save(shoponlines);
//			} catch (IOException e) {
//				throw new IOException("Could not  save uploaded file: " + fileName);
//			}
//		}


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
