package com.poly.DATN_BookWorms.rest.controller;


import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bookings")
public class BookingRestController {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	AccountService accountService;

	@Autowired
	HttpServletRequest httpServletRequest;
	@Autowired
	CRC32_SHA256 crc;
	@PostMapping()
	public Bookings create(@RequestBody JsonNode bookingData, HttpServletRequest request) {
//		try {
//			MailInformation mail = new MailInformation();
//			String username = request.getRemoteUser();
//			Account currentUser = accountService.findById(username);
//
//			mail.setTo(currentUser.getEmail());
//			String subject = "IBook Chào Bạn!";
//			String body = "Cảm ơn bạn đã đặt hàng của chúng tôi!";
//			mail.setSubject(subject);
//			mail.setBody(body);
//			mailer.send(mail);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return bookingService.create(bookingData);
		return null;
	}
	@GetMapping
	public List<Bookings> getAll(){

		return bookingService.findAll();
	}
	@GetMapping("/user")
	public List<Bookings> booking(){

		return bookingService.findByUserId(crc.getCodeCRC32C(httpServletRequest.getRemoteUser()));
	}

}
