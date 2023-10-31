package com.poly.DATN_BookWorms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Ibook/seller")
public class SellerController {
    @RequestMapping("/index")
    public String seller(Model model){
        return "SellerChannel/index";
    }
    @RequestMapping("/homePageSeller")
    public String homePageSeller(Model model){
        return "SellerChannel/homePageSeller";
    }
    @RequestMapping("/orderManagement/sales")
    public String sales(Model model){
        return "SellerChannel/DashboardSales";
    }
}
