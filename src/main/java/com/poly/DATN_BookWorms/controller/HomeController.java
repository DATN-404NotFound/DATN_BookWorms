package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
