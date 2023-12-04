package com.poly.DATN_BookWorms.service;

import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Bookings;


public interface BookingService {

	Bookings create(JsonNode bookingData);

	Object findById(String id);

	public List<Bookings> findByUserId(String userId);
	public List<Bookings> findAll();
	public List<Bookings> findByStatusId(String orderStatusId);
	
	public Bookings update(Bookings bookingId);
	
	public Bookings byBookingUserId(String bookingUserId);
	
	

}
