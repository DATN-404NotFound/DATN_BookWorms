package com.poly.DATN_BookWorms.controllers.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.services.PaymentShopService;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	PaymentShopService paymentShopService;

	@GetMapping("/callpayment")
	public String callPayment(Model model) {

		List<PaymentShop> item = paymentShopService.findAll();
		System.out.println("hi" + item);
		model.addAttribute("item", item);
		return "admin/payment";
	}
}
