package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entities.Bookings;

@Service
public interface BookingService {

	Bookings create(JsonNode bookingData);

	Object findById(String id);

	List<Bookings> findByUsername(String username);

}
