package com.poly.DATN_BookWorms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/account")
public class AccountController {
		
	@GetMapping("/login")
	public String login() {
		return "Client/Account_page/Login";
	}
	@RequestMapping("index")
	public String home(){
		return "Client/index";
	}
}
