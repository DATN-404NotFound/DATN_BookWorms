package com.poly.DATN_BookWorms.rest.controller;


import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.CartService;
import com.poly.DATN_BookWorms.service.MailService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.MailBody;
import com.poly.DATN_BookWorms.utils.SessionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.BookingService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bookings")
public class BookingRestController {
	
	private static final Logger logger = LogManager.getLogger();
	
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	AccountService accountService;
	
    @Autowired
    SessionService sessionService;
    
    @Autowired
    MailBody mailBody;
    
    @Autowired
    MailService mailService;

    @Autowired
    CRC32_SHA256 crc32_SHA256;

	@Autowired
	HttpServletRequest httpServletRequest;
	

	@Autowired
	CRC32_SHA256 crc;
	@PostMapping()
	public Bookings create(@RequestBody JsonNode bookingData, HttpServletRequest request) {
		return bookingService.create(bookingData);
	
	}
	@GetMapping("")
	public List<Bookings> getAll() {
		return bookingService.findAll();	
	}
	// @GetMapping
	// public List<Bookings> getAll(){

	// 	return bookingService.findAll();
	// }
	@GetMapping("/user")
	public List<Bookings> booking(){

		return bookingService.findByUserId(crc.getCodeCRC32C(httpServletRequest.getRemoteUser()));
	}

	@GetMapping("/{bookingId}")
	public List<Detailbookings> getBookingId(@PathVariable String bookingId ) {
		Bookings b = bookingService.findById(bookingId).get();
	List<Detailbookings> d = b.getListOfDetailbookings();
		return d;
	}
	
	@PostMapping("/update")
	public Bookings updateStatusBooking(@RequestBody Bookings json) throws MessagingException{
		logger.info("huỷ starty...");
		System.out.println("huỷ hoá đơn2");
		 Bookings b = bookingService.findById(json.getBookingid()).get();
		b.setOrderstatusid(6);
		try {
			logger.info("huỷ starty...1");
			System.out.println("huỷ hoá đơn1");
			Account account = accountService.findByUserId(crc.getCodeCRC32C(httpServletRequest.getRemoteUser()));
			  String subject ="THÔNG BÁO XÁC NHẬN HUỶ ĐƠN HÀNG";
			  String personCancle = "";
			  if(json.userid.equals(account.userid)) { 
				  personCancle = "Người mua";
			  }
			  else { 
				  personCancle = "Người bán";
			  }
			  String buyer = mailBody.mailHuyDon(b.account.getFullname(), personCancle, b, "Huỷ Thành Công");
			  String shoper = mailBody.mailHuyDon(b.getListOfDetailbookings().get(0).books.shoponlines.getShopname(),personCancle,b, "Huỷ Thành Công");
			  bookingService.update(b);
			mailService.send(b.account.getEmail(),subject, buyer);
			mailService.send(b.getListOfDetailbookings().get(0).books.shoponlines.account.getEmail(),subject, shoper);
			System.out.println("huỷ hoá đơn");
		} catch (Exception e) {
			System.out.println("lỗi không huỷ đơn đc : "+ e);
			logger.info("huỷ end...1");
			// TODO: handle exception
		}
		return json;
	}

}
