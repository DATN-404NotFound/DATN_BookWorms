package com.poly.DATN_BookWorms.controller;

import java.util.List;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.CartService;
import com.poly.DATN_BookWorms.service.SaleService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;

	@Autowired
	SaleService saleService;

	@Autowired
	SessionService sessionService;
	
	@RequestMapping
	public String cartView(Model model) {
		Account user = sessionService.get("user");
			List<Cart> cartuser_list = cartService.findByUser(user.getUserid());
			model.addAttribute("cartuserList", cartuser_list);
			List<Shoponlines> list_cart_shop = cartService.list_cart_shop(user.getUserid());
			model.addAttribute("cartshoplist", list_cart_shop);
			List<Sales> sale_shopid_intendFor = saleService.saleOfShopIntendFor("ForBook");
			model.addAttribute("saleShopIntendFor",sale_shopid_intendFor);
			System.out.println("run: "+cartuser_list.size() );
			System.out.println("run: "+ list_cart_shop.size() );
		return "Client/cart_client/cart_user";
	}
	
	
//	@RequestMapping("/shopOnline")
//	public String cartLinkShop(@RequestParam("shopId") Integer shopId) { 
//		
//		return "Client/Product_page/product_shop_list";
//	}
	
	
//	@RequestMapping("/product")
//	public String cartLinkProduct() { 
//		return "Client/Product_page/detail_product";
//	}
//	
	
//	@RequestMapping("/product")
//	public String d() { 
//		return "Client/Product_page/product_shop_list";
//	}
//		@GetMapping("cart/client")
//	public String e() { 
//		return "Client/cart_client/cart_user";
//	}
//			@GetMapping("purch")
//	public String g() { 
//		return "Client/cart_client/deal";
//	}
}
