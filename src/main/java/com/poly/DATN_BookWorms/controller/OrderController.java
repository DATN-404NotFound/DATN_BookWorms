package com.poly.DATN_BookWorms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.CartService;
import com.poly.DATN_BookWorms.service.SaleService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
    @Autowired
    CartService cartService;

    @Autowired
    SaleService saleService;
	
	@GetMapping
	public String orderPage(Model model) { 
		  List<Cart> cartuser_list = cartService.findByUser();
	        model.addAttribute("cartuserList", cartuser_list);
	        List<Shoponlines> list_cart_shop = cartService.list_cart_shop();
	        model.addAttribute("cartshoplist", list_cart_shop);
	        List<Sales> sale_shopid_intendFor = saleService.saleOfShopIntendFor("D");
	        model.addAttribute("saleShopIntendFor", sale_shopid_intendFor);
//	        String username = request.getRemoteUser();
//	        model.addAttribute("requestusername", username);
		return "Client/cart_client/deal";
	}
}
