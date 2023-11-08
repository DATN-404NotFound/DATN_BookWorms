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
import com.poly.DATN_BookWorms.repo.BookingsRepo;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.service.BookingService;

@Service
public class BookingServiceImp implements BookingService{

	@Autowired
	BookingsRepo bookingRepo;
	@Autowired
	DetailbookingsRepo detailRepo;
	
	@Override
	public Bookings create(JsonNode bookingData) {
		ObjectMapper mapper = new ObjectMapper();
		Bookings booking = mapper.convertValue(bookingData, Bookings.class);
		bookingRepo.save(booking);
		TypeReference<List<Detailbookings>> type = new TypeReference<List<Detailbookings>>() {};
		List<Detailbookings> details = mapper.convertValue(bookingData.get("listOfDetailbookings"), type)
					.stream().peek(d -> d.setBookings(booking)).collect(Collectors.toList());
		detailRepo.saveAll(details);
		return booking;
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

//	@Override
//	public List<Bookings> findByUsername(String username) {
//		// TODO Auto-generated method stub
//		return bookingRepo.findByUsername(username);
//	}

	@Override
	public List<Bookings> findAll() {
		// TODO Auto-generated method stub
		return bookingRepo.findAll();
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
}
