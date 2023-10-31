package com.poly.DATN_BookWorms.controller;

import com.itextpdf.text.pdf.qrcode.Mode;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.CategoryService;
import com.poly.DATN_BookWorms.service.RoleService;
import com.poly.DATN_BookWorms.utils.SessionService;
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

    @Autowired
    RoleService roleService;

    @RequestMapping("/index")
    public String home(Model model) {
        //get user on session
        Account user = sessionService.get("user");

        //get and set info home page
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



        //request info user to header
        header(model, user);
        //

        model.addAttribute("user", user);
        return "Client/header_footer_index/aaa";
    }

    @RequestMapping("/header")
    public String header(Model model, Account user) {
        System.out.println(user.getFullname());

        if (user !=null){
            model.addAttribute("image", user.getImage());
            model.addAttribute("name", user.getFullname());

            Roles roles = roleService.findSellerByRoleId("SELLER");
        }
        return "Client/header_footer_index/header_index";
    }

    @RequestMapping("/seller")
    public String seller(Model model){
        return "SellerChannel/index";
    }

}
