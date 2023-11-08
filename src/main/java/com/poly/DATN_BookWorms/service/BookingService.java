package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Bookings;

@Service
public interface BookingService {

	Bookings create(JsonNode bookingData);

	Object findById(String id);

	List<Bookings> findByUsername(String username);
	
	List<Bookings> findAll();

	long countUnpaid();
	
	long countPaid();

	long countConfirm();

	long countDelivering();

	long countProcessed();

	long countCancel();

	long countRefund();

	List<Bookings> unpaid();

	List<Bookings> paid();

	List<Bookings> confirm();

	List<Bookings> delivering();

	List<Bookings> processed();

	List<Bookings> cancel();

	List<Bookings> refund();

}
