package com.poly.DATN_BookWorms.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.entities.Payments;
import com.poly.DATN_BookWorms.repo.BookingsRepo;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.PaymentService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BookingServiceImp implements BookingService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	BookingsRepo bookingRepo;
	@Autowired
	DetailbookingsRepo detailRepo;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	BooksRepo booksRepo;
	
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	
	@Override
	public Bookings create(JsonNode bookingData) {
		logger.info("create bookings start.....");
		logger.info("input JsonNode with bookingData : {}",bookingData.toString());
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			Bookings booking = mapper.convertValue(bookingData, Bookings.class);
			logger.info("have booking for create : {}",booking.toString());
			String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
			logger.info("userid for booking : {}",userid);
			booking.setUserid(userid);
			booking.setBookingid(crc32_SHA256.getCodeCRC32C(booking.getUserid()+booking.getCreateat()+ booking.getBookingid()));
			booking.getAccount().setUserid(userid);
			System.out.println("IN booking "+ booking.toString());
			bookingRepo.save(booking);
			logger.info("Create booking is successful with booking : {}", booking);
			TypeReference<List<Detailbookings>> type = new TypeReference<List<Detailbookings>>() {};
			TypeReference<List<Payments>> type1 = new TypeReference<List<Payments>>() {};
			List<Detailbookings> details = mapper.convertValue(bookingData.get("listOfDetailbookings"), type);
			logger.info("list detalbooking in booking have size : {}", details.size());
			logger.info("list detalbooking start.... ");
						details.stream().peek(d ->{
							
							if(d == null) { 
								
							}
							else { 
								logger.info("Detailbooking : {}", d.toString());
								Books books = booksRepo.findById((long) d.bookid).get();
								
								books.setQuantitysold(books.getQuantitysold() + d.getQuantity());
								books.setQuantity(books.getQuantity() - d.getQuantity());
								
								d.setBookingid(booking.getBookingid());
								 d.setBookings(booking);
								 d.setDbid(crc32_SHA256.getCodeCRC32C(userid+d.getBookid()));
								 logger.info("Detailbooking for create : {}", d.toString());
								 detailRepo.save(d);
								 booksRepo.save(books);
								 logger.info("Detailbooking for create is success full : {}", d.toString());
							}
						}).collect(Collectors.toList());

						
			List<Payments> payment = mapper.convertValue(bookingData.get("listOfPayments"), type1);
			logger.info("list Payment in booking have size : {}", payment.size());
			logger.info("list Payment start.... ");
			payment.stream().peek(d -> 
					{
						logger.info("Payment : {}", d.toString());
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
							System.out.println("payment now");
							d.setStatus("Chưa thanh toán");
							booking.getOrderstatuses().setOrderstatusid(3);
							booking.setOrderstatusid(3);
							bookingRepo.save(booking);	
							System.out.println("payment nows");}
						
					}).collect(Collectors.toList());
			logger.info("list Payment in booking have size : {}", payment.size());
			paymentService.saveAll(payment);
			logger.info("Create bookings, detailbookings, payments is successfully");
			return booking;
		} catch (Exception e) {
			logger.error("Create bookings, detailbookings, payments is failed with error : {}", e);
			return null;
			// TODO: handle exception
		}
	}

	@Override
	public Optional<Bookings> findById(String id) {
		// TODO Auto-generated method stub
		return  bookingRepo.findById(id);
	}

	@Override
	public List<Bookings> findByUserId(String userId) {
		logger.info("get booking by userid have userId : {}", userId);
		return bookingRepo.findBookingByUser(userId);
	}

	@Override
	public List<Bookings> findAll() {
		return bookingRepo.findAll();
	}

	@Override

	public List<Bookings> findByStatusId(String orderStatusId) {
		logger.info("get booking by orderStatusId have orderStatusId : {}", orderStatusId);
		return bookingRepo.ListBookings_Status(orderStatusId);
	}


	@Override
	public List<Bookings> findAllByUserId(String userId) {
		return bookingRepo.findByuserid(userId);
	}
	@Override
	public List<Bookings> findByUserIdAndOrderStatusId(String userId, Integer orderStatusId) {
		List<Bookings> allByUserId = findAllByUserId(userId);

		return allByUserId.stream()
				.filter(booking -> booking.getOrderstatusid().equals(orderStatusId))
				.collect(Collectors.toList());
	}

	
	@Override
	public List<Bookings> unpaid() {
		// TODO Auto-generated method stub
		return bookingRepo.unpaid();
	}

	@Override
	public List<Bookings> paid() {
		// TODO Auto-generated method stub
		return bookingRepo.paid();
	}

	@Override
	public List<Bookings> confirm() {
		// TODO Auto-generated method stub
		return bookingRepo.confirm();
	}

	@Override
	public List<Bookings> delivering() {
		// TODO Auto-generated method stub
		return bookingRepo.delivering();
	}

	@Override
	public List<Bookings> processed() {
		// TODO Auto-generated method stub
		return bookingRepo.processed();
	}

	@Override
	public List<Bookings> cancel() {
		// TODO Auto-generated method stub
		return bookingRepo.cancel();
	}

	@Override
	public List<Bookings> refund() {
		// TODO Auto-generated method stub
		return bookingRepo.refund();
	}
	public long countUnpaid() {
        return bookingRepo.countUnpaid();
    }
	
	public long countPaid() {
        return bookingRepo.countPaid();
    }
	
	public long countConfirm() {
        return bookingRepo.countConfirm();
    }
	
	public long countDelivering() {
        return bookingRepo.countDelivering();
    }
	
	public long countProcessed() {
        return bookingRepo.countProcessed();
    }
	
	public long countCancel() {
        return bookingRepo.countCancel();
    }
	
	public long countRefund() {
        return bookingRepo.countRefund();
    }

	@Override
	public long countBooking() {
		// TODO Auto-generated method stub
		return bookingRepo.count();
	}

}
