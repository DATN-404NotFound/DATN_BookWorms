package com.poly.DATN_BookWorms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Sales;

@Controller
public class VoucherController {

	@RequestMapping("createvoucher")
	public String voucher(Model model) {
		Sales sale = new Sales();

		model.addAttribute("sale", sale);

		return "admin/createvoucher";
	}
}
