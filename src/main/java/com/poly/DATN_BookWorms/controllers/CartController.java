package com.poly.DATN_BookWorms.controllers;

import java.util.List;

import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.DiscountCode;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import com.poly.DATN_BookWorms.services.CartService;
import com.poly.DATN_BookWorms.services.DiscountCodeService;
import com.poly.DATN_BookWorms.services.SaleService;

@Controller
@RequestMapping("/cart")
public class CartController {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	CartService cartService;

	@Autowired
	SaleService saleService;
	
	@Autowired
	DiscountCodeService discountCodeService;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	CRC32_SHA256 crc32_SHA256;
	

	@RequestMapping
	public String cartView(Model model, HttpServletRequest request) {
		try {
			List<Cart> cartuser_list = cartService.findByUser();
			model.addAttribute("cartuserList", cartuser_list);
			List<ShopOnline> list_cart_shop = cartService.list_cart_shop();
			model.addAttribute("cartshoplist", list_cart_shop);
			
			String username = request.getRemoteUser();
			model.addAttribute("requestusername", username);
			  List<DiscountCode> findDisountForSys = discountCodeService.findDisountForSys(crc32_SHA256.getCodeCRC32C(req.getRemoteUser()));
	           System.out.println("in dis1 "+ findDisountForSys);
			model.addAttribute("saleShopIntendFor", findDisountForSys);
			logger.info("get Cart page");
		} catch (Exception e) {
			logger.info("Error during cart controller with error :{}", e);
		}
		return "client_template/cart_client/cart_user";
	}

	@RequestMapping("/delete/{cartid}")
	public String deletecart(@PathVariable("cartid") Long cartid) {
		cartService.delete(cartid);
		return "redirect:/cart";
	}

}
