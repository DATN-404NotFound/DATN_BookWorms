package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.response.DetailBookingResponse;

@Service
public interface DetailBookingService {
	List<Books> findTop5Seller();
	
	List<Books> findTop5Inventory();
}
