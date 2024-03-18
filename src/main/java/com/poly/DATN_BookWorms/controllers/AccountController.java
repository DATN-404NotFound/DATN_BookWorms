package com.poly.DATN_BookWorms.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;

import com.poly.DATN_BookWorms.dto.AccountDto;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.services.AccountService;
import com.poly.DATN_BookWorms.services.serviceImplements.ICustomUserDetail;

import com.poly.DATN_BookWorms.services.MailService;
import com.poly.DATN_BookWorms.services.SaleService;
import com.poly.DATN_BookWorms.services.serviceImplements.IMail;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.MailBody;
import com.poly.DATN_BookWorms.utils.OTP_privateKey;
import com.poly.DATN_BookWorms.utils.SessionService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ICustomUserDetail ICustomUserDetail;

    @Autowired
    MailService mailService;

    @Autowired
    OTP_privateKey otp_privateKey;

    @Autowired
    SessionService sessionService;

    @Autowired
    MailBody mailBody;

    @Autowired
    CRC32_SHA256 crc32_SHA256;

    @Autowired
    SaleService saleService;

    @Autowired
    IMail mailer;

    @RequestMapping("/login")
    public String loginForm() {
        return "/client_template/account_page/login";
    }

    @RequestMapping("/login/success/google")
    public String loginWithGoogle(@AuthenticationPrincipal OAuth2User performance) {
        if (performance == null) {
            return "redirect:/login";
        }

        if (accountService.findByUsename(performance.getName()) == null) {
            AccountDto accountDTO = new AccountDto();
            accountDTO.setEmail(performance.getAttribute("email"));
            accountDTO.setUsername(performance.getName());
            accountDTO.setFullname(performance.getAttribute("name"));
            accountDTO.setPassword(String.valueOf(RandomStringUtils.randomAlphabetic(8)));
            accountService.save(accountDTO);

        }
        try {
            // Sleep for 2 seconds (2000 milliseconds)
            Thread.sleep(5000);
            ICustomUserDetail.loadUserByUsername(performance.getName());

        } catch (InterruptedException e) {
            ICustomUserDetail.loadUserByUsername(performance.getName());

        }
        return "redirect:/Ibook/index";
    }

    @RequestMapping("/login/success/facebook")
    public String loginWithFaceBook(@AuthenticationPrincipal OAuth2User performance) {
        if (performance == null) {
            return "redirect:/login";
        }
        System.out.println(performance.toString());
        if (accountService.findByUsename(performance.getName()) == null) {
            AccountDto accountDTO = new AccountDto();
            accountDTO.setEmail("");
            accountDTO.setUsername(performance.getAttribute("id"));
            accountDTO.setFullname(performance.getAttribute("name"));
            accountDTO.setPassword(String.valueOf(RandomStringUtils.randomAlphabetic(8)));
            accountService.save(accountDTO);

        }

        try {
            // Sleep for 2 seconds (2000 milliseconds)
            Thread.sleep(5000);
            ICustomUserDetail.loadUserByUsername(performance.getName());
        } catch (InterruptedException e) {
            ICustomUserDetail.loadUserByUsername(performance.getName());
            System.out.println("Interrupted Exception: "+e.getMessage()+e.getCause());
        }

        return "redirect:/Ibook/index";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        AccountDto user = new AccountDto();
        model.addAttribute("user", user);
        return "client_template/account_page/register";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") AccountDto accountDTO, BindingResult result, Model model) throws MessagingException {
        Account existingUser = accountService.findByUsename(accountDTO.getUsername());

        if (existingUser != null)
            result.rejectValue("Username", null, "Người dùng đã đăng ký!!!");
        if (result.hasErrors()) {
            model.addAttribute("user", accountDTO);
            return "client_template/account_page/register";
        }
        accountService.save(accountDTO);
        return "redirect:login";
    }


    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        return "client_template/account_page/forgotPassword";
    }

    @PostMapping("/forgotPasswordAction")
    public String forgotPassword(@RequestParam("username") String username, Model model) {
        String userid = crc32_SHA256.getCodeCRC32C(username);
        Account account = accountService.findByUserId(userid);
        if (account == null) {
            return "client_template/account_page/forgotPassword";
        } else {
            try {
                int OTP = otp_privateKey.OTP();
                String subject = "XÁC NHẬN DANH TÍNH NGƯỜI SỬ DỤNG IBOOK";
                String body = mailBody.mailBody(account.getFullname(), OTP);
                mailService.send(account.getEmail(), subject, body);
                sessionService.set("OTP", OTP);
                sessionService.set("acc", account);
            } catch (MessagingException e) {
                System.out.println("Lỗi khi gửi mail: " + e);
                return "client_template/account_page/forgotPassword";
            }

            return "client_template/account_page/confirmCode";
        }


    }

    @GetMapping("/newpass")
    public String newPass() {
        return "client_template/account_page/newPassword";
    }


    @GetMapping("/otpcon")
    public String otpcon() {
        return "client_template/account_page/confirmCode";
    }
}
