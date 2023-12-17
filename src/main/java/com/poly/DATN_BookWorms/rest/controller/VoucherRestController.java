package com.poly.DATN_BookWorms.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.poly.DATN_BookWorms.beans.MailInformation;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.SaleService;
import com.poly.DATN_BookWorms.service.impl.MailServiceImp;

import jakarta.mail.MessagingException;

@RestController
public class VoucherRestController {

	@Autowired
	SaleService saleService;
	@Autowired
	AccountService accountService;
	@Autowired
	MailServiceImp mailer;
	
	
	@PostMapping("/createvoucher")
	public ResponseEntity<String> createVoucher(Sales sale) throws MessagingException {
		List<Account> acc = accountService.findAll();
		
		for (Account account : acc) {
			saleService.save(sale);
			MailInformation mailInfo = new MailInformation();
			mailInfo.setTo(account.getEmail());
			mailInfo.setSubject("IBook chào bạn! Thông tin voucher mới");
			String body = "Bạn hãy vào My Account -> List Voucher để kiểm tra nhé";
			mailInfo.setBody(body);
			mailer.send(mailInfo);
		}
		
        return new ResponseEntity<>("Vouchers created for all users", HttpStatus.CREATED);
	}
	
}
