package com.poly.DATN_BookWorms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.dto.AccountDTO;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/login")
	public String loginForm() {
		return "Client/Account_page/Login";
	}

	@GetMapping("/registration")
	public String registrationForm(Model model) {
		AccountDTO user = new AccountDTO();
		model.addAttribute("user", user);
		return "Client/Account_page/Register";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") AccountDTO accountDTO, BindingResult result, Model model) {
		Account  existingUser = accountService.findByUsename(accountDTO.getUsername());

		if (existingUser != null)
			result.rejectValue("Username", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("user", accountDTO);
			return "Client/Account_page/Register";
		}

		accountService.save(accountDTO);
		return "redirect:/login";
	}
}
