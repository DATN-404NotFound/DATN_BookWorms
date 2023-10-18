package com.poly.rest.controller;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.beans.MailInformation;
import com.poly.entities.*;
import com.poly.service.*;
import com.poly.service.impl.MailServiceImp;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bookings")
public class BookingRestController {
	@Autowired
	BookingService bookingService;
	@Autowired
	MailServiceImp mailer;
	@Autowired
	AccountService accountService;

	@PostMapping()
	public Bookings create(@RequestBody JsonNode bookingData, HttpServletRequest request) {
		try {
			MailInformation mail = new MailInformation();
			String username = request.getRemoteUser();
			Account currentUser = accountService.findById(username);

			mail.setTo(currentUser.getEmail());
			String subject = "IBook Chào Bạn!";
			String body = "Cảm ơn bạn đã đặt hàng của chúng tôi!";
			mail.setSubject(subject);
			mail.setBody(body);
			mailer.send(mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookingService.create(bookingData);
	}

}
