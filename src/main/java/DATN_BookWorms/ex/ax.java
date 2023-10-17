package DATN_BookWorms.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ax {

	@GetMapping("/a")
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
}
