package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.repositories.BooksRepo;

@CrossOrigin("*")
@RestController

@RequestMapping("/rest/admin")
public class AdminRestController {

    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/bestSeller")
    public ResponseEntity<List<Book>> bestSeller() {
        List<Book> listBooks = booksRepo.findTop5Seller();
        System.out.println(listBooks.toString());
        return ResponseEntity.ok(listBooks);

    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Book>> inventory() {
        List<Book> listBooks = booksRepo.findTop5Inventory();
        return ResponseEntity.ok(listBooks);
    }

}
