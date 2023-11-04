package com.poly.DATN_BookWorms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Discountcodes;
import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.entities.Shippingunits;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.service.AccountAddressService;
import com.poly.DATN_BookWorms.service.CartService;
import com.poly.DATN_BookWorms.service.DiscountCodeService;
import com.poly.DATN_BookWorms.service.SaleService;
import com.poly.DATN_BookWorms.service.ShippingUnitService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	CartService cartService;

	@Autowired
	SaleService saleService;

	@Autowired
	CRC32_SHA256 crc32_SHA256;

	@Autowired
	HttpServletRequest request;

	@Autowired
	AccountAddressService accountAddressService;

	@Autowired
	DiscountCodeService discountCodeService;

	@Autowired
	ShippingUnitService shippingUnitService;

	@GetMapping
	public String orderPage(Model model) {
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		List<Addressusers> addressusser = accountAddressService.getAdressByUser(userid);
		model.addAttribute("addressusser", addressusser);

		List<Discountcodes> discountcode = discountCodeService.findDisountForSys(userid);
		model.addAttribute("discountcode", discountcode);
		
		  List<Shippingunits> shippingunit = shippingUnitService.getAll();
	        model.addAttribute("shippingunit", shippingunit);
//	        List<Shoponlines> list_cart_shop = cartService.list_cart_shop();
//	        model.addAttribute("cartshoplist", list_cart_shop);
//	        List<Sales> sale_shopid_intendFor = saleService.saleOfShopIntendFor("D");
//	        model.addAttribute("saleShopIntendFor", sale_shopid_intendFor);
//	        String username = request.getRemoteUser();
//	        model.addAttribute("requestusername", username);
		return "Client/cart_client/deal";
	}
}
