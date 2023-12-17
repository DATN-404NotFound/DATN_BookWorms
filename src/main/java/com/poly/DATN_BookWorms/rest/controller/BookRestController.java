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
ShopService shopService;

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
	public List<Books> getAll5(@PathVariable("id") Integer id) {}


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


	@PutMapping("{id}")
	public Books update(@PathVariable("id") Integer id, @RequestBody Books book) {
		return bookService.update(book);
	}


	@GetMapping("/shop")
	public List<Books> getBookListShop(@RequestParam("shopid") Integer shopid) {

		return bookService.findByShopList(shopid);
	}

	@GetMapping("/type")
	public List<Integer> getBookWithTypeBook(@RequestParam("listtype") String listtype) {
		String[] listty = listtype.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for (int i = 0; i < listty.length; i++) {
			listtypes.add(Integer.parseInt(listty[i]));
		}

		return bookService.getBookWithTypeBook(listtypes);
	}

	@GetMapping("/writer")
	public List<Integer> getBookWithWriter(@RequestParam("listwriter") String listwriter) {
		String[] listty = listwriter.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for (int i = 0; i < listty.length; i++) {
			listtypes.add(Integer.parseInt(listty[i]));
		}

		return bookService.getBookWithWriters(listtypes);
	}

	@GetMapping("/Eva")
	public List<Integer> getBookWithEvaluate(@RequestParam("listeva") String listeva) {
		String[] listty = listeva.split(",");
		List<Integer> listtypes = new ArrayList<Integer>();
		for (int i = 0; i < listty.length; i++) {
			listtypes.add(Integer.parseInt(listty[i]));
		}
		return bookService.getBookWithEvaluate(listtypes);
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

	@GetMapping("/writers")
	public ResponseEntity<List<Writtingmasters>> getAllWriter() {
		List<Writtingmasters> writers = writerService.findAll();

		if (writers != null && !writers.isEmpty()) {
			return new ResponseEntity<>(writers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<String> createBook(@RequestParam("bookname") String bookname,
			@RequestParam("category") Integer category, @RequestParam("language") String language,
			@RequestParam("size") String size, @RequestParam("weight") Double weight,
			@RequestParam("totalpage") Integer totalpage, @RequestParam("publishingyear") Integer publishingyear,
			@RequestParam("price") Double price, @RequestParam("quantity") Integer quantity,
			@RequestParam("publishingcompanyid") Integer publishingcompanyid,
			@RequestParam("isactive") Boolean isactive, @RequestPart("images") MultipartFile[] images) {
		try {
			if (category == null || images == null || images.length == 0) {
				return ResponseEntity.badRequest().body("Invalid category or empty images.");
			}
			Books book = bookService.creates(bookname, language, size, weight, totalpage, publishingyear, price,
					quantity, publishingcompanyid, isactive, images, category);
			if (book != null && book.getBookid() != null) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Book created successfully. Book ID: " + book.getBookid());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating book.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating book.");
		}
	}

	@PutMapping("/updateIsActive/{bookId}")
	public ResponseEntity<Void> updateIsActive(@PathVariable Long bookId,
			@RequestBody Map<String, Boolean> requestBody) {
		boolean newIsActive = requestBody.get("isactive");
		bookService.updateIsActive(bookId, newIsActive);
		return ResponseEntity.ok().build();
	}
}
