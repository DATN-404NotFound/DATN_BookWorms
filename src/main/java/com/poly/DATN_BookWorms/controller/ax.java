
package com.poly.DATN_BookWorms.controller;

<<<<<<< HEAD

=======
import org.springframework.security.core.Authentication;
>>>>>>> zendyy/back_end
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
<<<<<<< HEAD
	@GetMapping("/s")
	public String s() {
		return "Client/Product_page/product_list";
	}
=======
	
	@RequestMapping("/dashboard")
	 public String dashboard(Model model, Authentication authentication) {
	        if (authentication != null && authentication.getAuthorities().stream()
	                .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	            return "redirect:/admin/index";
	        } else {
	            return "redirect:/product/a";
	        }
	    }
>>>>>>> zendyy/back_end
}
