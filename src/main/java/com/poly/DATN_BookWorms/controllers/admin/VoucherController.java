package com.poly.DATN_BookWorms.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Sale;

@Controller
public class VoucherController {

	@RequestMapping("createvoucher")
	public String voucher(Model model) {
		Sale sale = new Sale();

		model.addAttribute("sale", sale);

		return "createVoucher";
	}
}
