package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    BookService bookService;

   @RequestMapping("index")
    public String home(Model model){
       List<Books> list = bookService.findAll();
       model.addAttribute("items", list);
    return "Client/header_footer_index/aaa";
    }
}
