package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.entities.Payments;
import com.poly.DATN_BookWorms.repo.BookingsRepo;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.PaymentService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BookingServiceImp implements BookingService{

	@Autowired
	BookingsRepo bookingRepo;
	@Autowired
	DetailbookingsRepo detailRepo;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	@Override
	public Bookings create(JsonNode bookingData) {
		ObjectMapper mapper = new ObjectMapper();
		Bookings booking = mapper.convertValue(bookingData, Bookings.class);
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		booking.setUserid(userid);
		booking.setBookingid(crc32_SHA256.getCodeCRC32C(booking.getUserid()+booking.getCreateat()));
		
		bookingRepo.save(booking);
		TypeReference<List<Detailbookings>> type = new TypeReference<List<Detailbookings>>() {};
		TypeReference<List<Payments>> type1 = new TypeReference<List<Payments>>() {};
		List<Detailbookings> details = mapper.convertValue(bookingData.get("listOfDetailbookings"), type)
					.stream().peek(d ->{
						
						 d.setBookings(booking);
						 d.setDbid(crc32_SHA256.getCodeCRC32C(userid+d.getBookid()));
					}).collect(Collectors.toList());
		List<Payments> payment = mapper.convertValue(bookingData.get("listOfPayments"), type1)
				.stream().peek(d -> 
				{
					d.setBookings(booking);
					d.setPaymentid(crc32_SHA256.getCodeCRC32C(userid+ d.getBookingid()));
					if(d.getType()) { 
						d.setStatus("Đã thanh toán");
						booking.getOrderstatuses().setOrderstatusid(4);
					}
					else { 
						d.setPaid(null);
						d.setPaymentaccounts(null);
						d.setStatus("Chưa thanh toán");
						booking.getOrderstatuses().setOrderstatusid(3);
					}
					
				}).collect(Collectors.toList());
		detailRepo.saveAll(details);
		paymentService.saveAll(payment);
		return booking;
		//return null;
	}

	@Override
	public Object findById(String id) {
		// TODO Auto-generated method stub
		return bookingRepo.findById(id);
	}

	@Override
	public List<Bookings> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return bookingRepo.findAll();
	}

//	@Override
//	public List<Bookings> findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return bookingRepo.findByUsername(username);
//	}

	
}
