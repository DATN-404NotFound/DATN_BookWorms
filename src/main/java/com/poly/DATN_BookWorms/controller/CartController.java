package com.poly.DATN_BookWorms.controller;

import java.util.List;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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



    @RequestMapping
    public String cartView(Model model,HttpServletRequest request) {

        List<Cart> cartuser_list = cartService.findByUser();
        model.addAttribute("cartuserList", cartuser_list);

        List<Shoponlines> list_cart_shop = cartService.list_cart_shop();
        model.addAttribute("cartshoplist", list_cart_shop);

        List<Sales> sale_shopid_intendFor = saleService.saleOfShopIntendFor("D");
        model.addAttribute("saleShopIntendFor", sale_shopid_intendFor);

        System.out.println("run: " + cartuser_list.size());
        System.out.println("run: " + list_cart_shop.size());
        String username = request.getRemoteUser();
        model.addAttribute("requestusername", username);
        System.out.println("kkkusser "+  username );
        return "Client/cart_client/cart_user";
    }

    @RequestMapping("/delete/{cartid}")
    public String deletecart(@PathVariable("cartid") Integer cartid) { 
//    	cartService.delete(cartid);
    	System.out.println("xmm");
    	cartService.delete(cartid);
    	return "redirect:/cart";
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
