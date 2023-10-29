package com.poly.DATN_BookWorms.controller;

import com.itextpdf.text.pdf.qrcode.Mode;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Ibook")
public class HomeController {
    @Autowired
    BookService bookService;

    @Autowired
    SessionService sessionService;

   @RequestMapping("/index")
    public String home(Model model){
        Books top = bookService.findTopBookByQuantitySold();
    model.addAttribute("item", top);
    List<Books> education = bookService.getBooksByCategoryID(2);
    List<Books> education1 = bookService.getBooksByCategoryID(4);
    List<Books> education2 = bookService.getBooksByCategoryID(7);
    model.addAttribute("education", education);
    model.addAttribute("education1", education1);
    model.addAttribute("education2", education2);
    if (education != null && !education.isEmpty()) {
        for (Books educationItem : education) {
            System.out.println("Tên sản phẩm: " + educationItem.getBookname());
            System.out.println("Giá sản phẩm: " + educationItem.getPrice());
            System.out.println();
        }
    } else {
        System.out.println("Không có dữ liệu.");
    }
       Account user =  sessionService.get("user");
       List<Books> list = bookService.findAll();

       //request info user to header
       model.addAttribute("image",user.getImage());
       model.addAttribute("name",user.getFullname());
       header(model, user);
       //

       model.addAttribute("user",user);
       model.addAttribute("items", list);
    return "Client/header_footer_index/aaa";
    }

    @RequestMapping("/header")
    public String header( Model model, Account user){
        System.out.println(user.getFullname());
        return "Client/header_footer_index/header_index";
    }




}
