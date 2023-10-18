package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entities.Bookings;
import com.poly.entities.Detailbookings;
import com.poly.repo.BookingsRepo;
import com.poly.repo.DetailbookingsRepo;
import com.poly.service.BookingService;

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
		return bookingRepo.findByUsername(username);
	}

	
}
