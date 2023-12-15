package com.poly.DATN_BookWorms.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.awt.print.Book;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.service.*;
import com.poly.DATN_BookWorms.utils.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/rest/books")
public class BookRestController {
    @Autowired
    BookService bookService;
    @Autowired
    ImagesBookService imagesBookService;
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

    @Autowired
    ShopService shopService;


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

        return bookService.getBooksByCategoryID(id);
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

    @PostMapping(value = "/createBook")
    public ResponseEntity<Books> createBook(@RequestBody @Valid Books book) {
        Account user = service.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());

        book.setShopid(shoponlines.getShopid());
        Books newBook = bookService.creates(book);
        return ResponseEntity.ok(newBook);
    }

    @PostMapping(value = "/createTypeBook")
    public ResponseEntity<Typebooks> createTypeBooks(@RequestParam("categoryid") String categoryId,@RequestParam("bookid") String bookId ) {
        Typebooks typebook = new Typebooks();
        typebook.setBookid(Integer.parseInt(bookId));
        typebook.setCategoryid(Integer.parseInt(categoryId));
        System.out.println(typebook.getCategoryid());
        typeBookService.save(typebook);
        return ResponseEntity.ok(typebook);
    }

    @PostMapping(value = "/createWriter")
    public ResponseEntity<Writers> createTypeBooks(@RequestBody @Valid Writers writers) {
        Writers writer = writerService.save(writers);
        return ResponseEntity.ok(writer);
    }

    @PostMapping(value = "/uploadImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUploadForm(
            @RequestPart("file") MultipartFile file,
            @RequestParam("bookId") String bookId) throws IOException {

        log.info("Handling request parts: {}", file);

        try {
            File f = new ClassPathResource("").getFile();
            String uploadDir = "D:/DATN/DATN_BookWorms/src/main/resources/static/SellerChannel/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!java.nio.file.Files.exists(uploadPath)) java.nio.file.Files.createDirectories(uploadPath);

            String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(uniqueFileName)
                    .toUriString();

            var result = Map.of(
                    "filename", uniqueFileName,
                    "fileUri", fileUri
            );
            Imagebooks imagebooks = new Imagebooks();
            imagebooks.setBookid(Integer.valueOf(bookId));
            imagebooks.setName(uniqueFileName);
            imagebooks.setTypefile("image");
            imagesBookService.save(imagebooks);
            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
