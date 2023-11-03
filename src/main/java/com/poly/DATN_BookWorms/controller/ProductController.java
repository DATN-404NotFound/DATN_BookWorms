package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Categories;
import com.poly.DATN_BookWorms.entities.Publishingcompanies;
import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.response.BookResponse;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.CategoryService;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;
import com.poly.DATN_BookWorms.service.WriterMasterService;
import com.poly.DATN_BookWorms.service.impl.BookServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PublishingCompanyService publishingCompanyService;
    @Autowired
    WriterMasterService writerMasterService;
    @GetMapping("/list")
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Categories> categories = categoryService.findAll();
        List<Publishingcompanies> publishingcompanies = publishingCompanyService.findAll();
        List<Writtingmasters> writtingmasters = writerMasterService.findAll();
        Page<Books> bookPage = bookService.findAll(pageable);

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("categories", categories);
        model.addAttribute("publishingcompanies", publishingcompanies);
        model.addAttribute("writtingmasters", writtingmasters);
        return "Client/Product_page/product_list";
    }




}
