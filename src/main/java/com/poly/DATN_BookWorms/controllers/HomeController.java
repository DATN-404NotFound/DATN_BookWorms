package com.poly.DATN_BookWorms.controllers;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.services.*;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    AuthoritiesService authoritiesService;

    @Autowired
    CRC32_SHA256 crc32Sha256;

    @Autowired
    ShopService shopService;

    @Autowired
    ShopOnlinesService shopOnlinesService;

    @Autowired
    AccountService accountService;

    @RequestMapping("/index")
    public String home(Model model, Authentication authentication) {
        //get user on session
        Account user = sessionService.get("user");

        //get and set info home page
        Book top = bookService.findTopBookByQuantitySold();
        List<ImageBook> listImage = top.getListOfImageBook();

        model.addAttribute("item", top);

        model.addAttribute("listImage", listImage);

        List<Book> education = bookService.getBooksByCategoryID(2);
        List<Book> education1 = bookService.getBooksByCategoryID(4);
        List<Book> education2 = bookService.getBooksByCategoryID(7);
        model.addAttribute("education", education);
        model.addAttribute("education1", education1);
        model.addAttribute("education2", education2);
        if (education != null && !education.isEmpty()) {
            for (Book educationItem : education) {
                System.out.println();
            }
        } else {
            System.out.println("Không có dữ liệu.");
        }


        //request info user to header
        header(model, user);
        //
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", new Account());
        }

        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return "redirect:/admin/index";
        } else {
            return "/client/header_footer_index/index";
        }
    }
    @RequestMapping("/header")
    public String header(Model model, Account user) {
        if (user != null) {
            model.addAttribute("image", user.getImage());
            model.addAttribute("name", user.getFullname());
            Role role = roleService.findSellerByRoleId("SELLER");

            ShopOnline shoponline = shopService.findUserId(user.getUserid());
            model.addAttribute("shop", shoponline);
        }
        return "client/header_footer_index/header_index";
    }

    @RequestMapping("/seller")
    public String seller(Model model) {
        Account user = sessionService.get("user");
        if (user == null) {
            return "redirect:/account/login";
        } else {
            //get data shop
            ShopOnline shoponline = shopService.findUserId(user.getUserid());
            model.addAttribute("shop", shoponline);

            return "sellerChannel/index";
        }
    }

    @RequestMapping("/createSeller")
    public String newSeller(Model model) {
        Account user = sessionService.get("user");

        // add role SEller if dont have ROLE SELLER
        Role role = roleService.findSellerByRoleId("SELLER");
        if (role == null) {
            role = roleService.save(new Role("SELLER", "Seller", null));
        }
        //Create ROLE SELLER TO USER
        String authorityId = crc32Sha256.getCodeCRC32C(user.getUsername() + role.getRoleid());
        authoritiesService.save(new Authority(authorityId, user, role));

        //create default shop with account
        if (user.getListOfShopOnline().isEmpty() || shopService.findById(user.getListOfShopOnline().get(0).getShopid()) == null) {
            shopService.createShopDefaultWithUser(user);
        }

        //Add Account to Session again
        Account user2 = accountService.findByUserId(user.getUserid());
        sessionService.set("user", user2);

        //get data shop
        ShopOnline shoponline = shopService.findUserId(user2.getUserid());
        model.addAttribute("shop", shoponline);
        return "sellerChannel/index";
    }

    @GetMapping("/shop")
    public String getAllShop(Model model) {
        List<ShopOnline> listshop = new ArrayList<ShopOnline>();
        listshop = shopOnlinesService.getAllListShop();
        model.addAttribute("shoplist", listshop);
        return "client/product_page/shop_list";
    }

    @GetMapping("/about")
    public String getBout(Model model) {

        return "client/header_footer_index/about";
    }
}
