package com.poly.DATN_BookWorms.restControllers;

import com.poly.DATN_BookWorms.entities.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.poly.DATN_BookWorms.responses.MailInformationResponse;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.services.AccountService;
import com.poly.DATN_BookWorms.services.SaleService;
import com.poly.DATN_BookWorms.services.serviceImplements.IMail;

import jakarta.mail.MessagingException;

@RestController
public class VoucherRestController {

	@Autowired
	SaleService saleService;
	@Autowired
	AccountService accountService;
	@Autowired
    IMail mailer;
	
	
	@PostMapping("/createvoucher")
	public ResponseEntity<String> createVoucher(Sale sale) throws MessagingException {
		List<Account> acc = accountService.findAll();
		
		for (Account account : acc) {
			saleService.save(sale);
			MailInformationResponse mailInfo = new MailInformationResponse();
			mailInfo.setTo(account.getEmail());
			mailInfo.setSubject("IBook chào bạn! Thông tin voucher mới");
			String body = "Bạn hãy vào My Account -> List Voucher để kiểm tra nhé";
			mailInfo.setBody(body);
			mailer.send(mailInfo);
		}
		
        return new ResponseEntity<>("Vouchers created for all users", HttpStatus.CREATED);
	}
	
}
