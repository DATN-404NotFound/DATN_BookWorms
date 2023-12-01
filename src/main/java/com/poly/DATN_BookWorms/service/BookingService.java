package com.poly.DATN_BookWorms.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Bookings;


public interface BookingService {

	Bookings create(JsonNode bookingData);

	Optional<Bookings> findById(String id);

	public List<Bookings> findByUserId(String userId);
	public List<Bookings> findAll();
	public List<Bookings> findByStatusId(String orderStatusId);




	List<Bookings> findAllByUserId(String userId);

	List<Bookings> findByUserIdAndOrderStatusId(String userId, Integer orderStatusId);
}
