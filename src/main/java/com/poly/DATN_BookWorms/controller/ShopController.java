package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.ShopOnlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopOnlinesService shopOnlinesService;
    @GetMapping("/{id}")
    public String profile(Model model, @PathVariable("id") Integer id) {
        Shoponlines list = shopOnlinesService.findById(id);
        model.addAttribute("profile", list);

        return "Client/Product_page/product_shop_list";
    }
}
