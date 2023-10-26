package com.poly.DATN_BookWorms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ax {

	@RequestMapping("/a")
	public String a() { 
		return "Client/header_footer_index/aaa";
	}
	
	@GetMapping("/b")
	public String b() { 
		return "Client/Product_page/detail_product";
	}
		
	@GetMapping("/c")
	public String c() { 
		return "Client/Product_page/product_list";
	}
	@GetMapping("/d")
	public String d() { 
		return "Client/Product_page/product_shop_list";
	}
		@GetMapping("cart/client")
	public String e() { 
		return "Client/cart_client/cart_user";
	}
			@GetMapping("purch")
	public String g() { 
		return "Client/cart_client/deal";
	}
	@GetMapping("/s")
	public String s() {
		return "Client/Product_page/product_list";
	}
}
