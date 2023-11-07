package com.poly.DATN_BookWorms.rest.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.BookingService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bookings")
public class BookingRestController {
	@Autowired
	BookingService bookingService;
	
	@Autowired
	AccountService accountService;

	@PostMapping()
	public Bookings create(@RequestBody JsonNode bookingData, HttpServletRequest request) {
		return bookingService.create(bookingData);
	
	}
	@GetMapping("")
	public List<Bookings> getAll() {
		return bookingService.findAll();	
	}

}
