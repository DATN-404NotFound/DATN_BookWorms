package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.response.BookResponse;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.CategoryService;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;
import com.poly.DATN_BookWorms.service.WriterMasterService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PublishingCompanyService publishingCompanyService;
    @Autowired
    WriterMasterService writerMasterService;

    @Autowired
    CRC32_SHA256 crc;
    
    @Autowired
    HttpServletRequest request;


    @GetMapping("/list")
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "8") int size,
                            @RequestParam(defaultValue = "asc") String priceSort) {

        Sort priceDirection;
        if ("asc".equalsIgnoreCase(priceSort)) {
            priceDirection = Sort.by(Sort.Direction.DESC, "price");

        } else {
            priceDirection = Sort.by(Sort.Direction.ASC, "price");
        }

        Sort sort = priceDirection;

        Pageable pageable = PageRequest.of(page, size, sort);
        List<Categories> categories = categoryService.findAll();
        List<Publishingcompanies> publishingcompanies = publishingCompanyService.findAll();
        List<Writtingmasters> writtingmasters = writerMasterService.findAll();
        Page<BookResponse> bookPage = bookService.findAllBook(pageable);

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("categories", categories);
        model.addAttribute("publishingcompanies", publishingcompanies);
        model.addAttribute("writtingmasters", writtingmasters);
        return "Client/Product_page/product_list";
    }



    @GetMapping("/detail/{bookid}")
	public String detail(@PathVariable("bookid") int id, Model model) {
        //  System.out.println("lkjlskjlajf");
		Books item = bookService.findById(id);
             System.out.println("lkjlskjlajssf"+ id);
		
//		List<String> images = imagebookService.findByBookId(id);
//		System.out.print(images);
//		model.addAttribute("images", images);
		model.addAttribute("item", item);
        model.addAttribute("im","Hinh4_book4.jpg");
        model.addAttribute("userid", crc.getCodeCRC32C(request.getRemoteUser()));
         System.out.println("ll"+ item.getListOfImagebooks().get(0).getName());
        return "Client/Product_page/detail_product";
	}
}
