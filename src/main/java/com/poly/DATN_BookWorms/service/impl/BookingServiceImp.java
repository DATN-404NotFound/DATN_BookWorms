package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Bookings booking = mapper.convertValue(bookingData, Bookings.class);
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		booking.setUserid(userid);
		booking.setBookingid(crc32_SHA256.getCodeCRC32C(booking.getUserid()+booking.getCreateat()));
		booking.getAccount().setUserid(userid);
		System.out.println("IN booking "+ booking.toString());
		bookingRepo.save(booking);
		TypeReference<List<Detailbookings>> type = new TypeReference<List<Detailbookings>>() {};
		TypeReference<List<Payments>> type1 = new TypeReference<List<Payments>>() {};
		List<Detailbookings> details = mapper.convertValue(bookingData.get("listOfDetailbookings"), type)
					.stream().peek(d ->{
						d.setBookingid(booking.getBookingid());
						 d.setBookings(booking);
						 d.setDbid(crc32_SHA256.getCodeCRC32C(userid+d.getBookid()));
						 
					}).collect(Collectors.toList());
		List<Payments> payment = mapper.convertValue(bookingData.get("listOfPayments"), type1)
				.stream().peek(d -> 
				{
					d.setBookingid(booking.getBookingid());
					d.setBookings(booking);
					d.setPaymentid(crc32_SHA256.getCodeCRC32C(userid+ d.getBookingid()));
					if(d.getType()) { 
						d.setStatus("Đã thanh toán");
						booking.getOrderstatuses().setOrderstatusid(4);
						booking.setOrderstatusid(4);
						bookingRepo.save(booking);	
					}
					else { 
						d.setPaid(null);
						d.setPaymentaccounts(null);
						d.setStatus("Chưa thanh toán");
						booking.getOrderstatuses().setOrderstatusid(3);
						booking.setOrderstatusid(3);
						bookingRepo.save(booking);					}
					
				}).collect(Collectors.toList());
		detailRepo.saveAll(details);
		paymentService.saveAll(payment);
		return booking;
		//return null;
	}

	@Override
	public Optional<Bookings> findById(String id) {
		// TODO Auto-generated method stub
		return  bookingRepo.findById(id);
	}

	@Override
	public List<Bookings> findByUserId(String userId) {
		return bookingRepo.findBookingByUser(userId);
	}

	@Override
	public List<Bookings> findAll() {
		return bookingRepo.findAll();
	}

	@Override
	public List<Bookings> findByStatusId(String orderStatusId) {
		return bookingRepo.ListBookings_Status(orderStatusId);
	}


//	@Override
//	public List<Bookings> findAllByUserId(String userId) {
//		return bookingRepo.findByuserid(userId);
//	}
//	@Override
//	public List<Bookings> findByUserIdAndOrderStatusId(String userId, Integer orderStatusId) {
//		List<Bookings> allByUserId = findAllByUserId(userId);
//
//		return allByUserId.stream()
//				.filter(booking -> booking.getOrderstatusid().equals(orderStatusId))
//				.collect(Collectors.toList());
//	}
	@Override
	public List<Bookings> findBookingsByShopIdAndOrderStatusID(Integer shopId, Integer orderStatusId) {
		List<Bookings> allBookings =bookingRepo.findBookingsByShopId(shopId);

		return allBookings.stream()
				.filter(booking -> booking.getOrderstatusid().equals(orderStatusId))
				.collect(Collectors.toList());
	}
	@Override
	public List<Bookings> findBookingsByShopId(Integer shopId) {
		return bookingRepo.findBookingsByShopId(shopId);
	}
//	@Override
//	public List<Bookings> findByShopIdAndOrderStatusId(Integer shopId, Integer orderStatusId) {
//		return bookingRepo.findBookingsByShopIdAndOrderStatusId(shopId, orderStatusId);
//	}

	@Override
	@Transactional
	public void updateOrderStatus(String bookingId) {
		Bookings booking = bookingRepo.findById(bookingId).orElse(null);
		if (booking != null) {
			booking.setOrderstatusid(4);
			bookingRepo.save(booking);
		}
	}
}
