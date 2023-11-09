package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Bookings;


public interface BookingService {

	Bookings create(JsonNode bookingData);

	Object findById(String id);

	public List<Bookings> findByUserId(String userId);
	public List<Bookings> findAll();
	public List<Bookings> findByStatusId(String orderStatusId);

}
