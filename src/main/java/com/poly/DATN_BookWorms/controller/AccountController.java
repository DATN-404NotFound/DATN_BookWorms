package com.poly.DATN_BookWorms.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import com.poly.DATN_BookWorms.service.CustomUserDetailService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @RequestMapping("/login")
    public String loginForm() {
        return "Client/Account_page/Login";
    }

    @RequestMapping("/login-google/success")
    public String loginWithGoogle(@AuthenticationPrincipal OAuth2User performance, Model model) {
        if (performance == null) {
            return "redirect:/login";
        }

        if (accountService.findByUsename(performance.getName()) == null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setEmail(performance.getAttribute("email"));
            accountDTO.setUsername(performance.getName());
            accountDTO.setFullname(performance.getAttribute("name"));
            accountDTO.setPassword(String.valueOf(RandomStringUtils.randomAlphabetic(8)));
            accountService.save(accountDTO);

        }
        customUserDetailService.loadUserByUsername(performance.getName());
        return "redirect:/Ibook/index";
    }
    
    @RequestMapping("/login-facebook/success")
    public String loginWithFaceBook(@AuthenticationPrincipal OAuth2User performance, Model model) {
        if (performance == null) {
            return "redirect:/login";
        }

        if (accountService.findByUsename(performance.getName()) == null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setEmail(performance.getAttribute("email"));
            accountDTO.setUsername(performance.getName());
            accountDTO.setFullname(performance.getAttribute("name"));
            accountDTO.setPassword(String.valueOf(RandomStringUtils.randomAlphabetic(8)));
            accountService.save(accountDTO);

        }
        customUserDetailService.loadUserByUsername(performance.getName());
        return "redirect:/Ibook/index";
    }
    

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        AccountDTO user = new AccountDTO();
        model.addAttribute("user", user);
        return "Client/Account_page/Register";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") AccountDTO accountDTO, BindingResult result, Model model) {
        Account existingUser = accountService.findByUsename(accountDTO.getUsername());

        if (existingUser != null)
            result.rejectValue("Username", null, "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", accountDTO);
            return "Client/Account_page/Register";
        }
        accountService.save(accountDTO);
        return "redirect:login";
    }
}
