package com.poly.DATN_BookWorms.service;

<<<<<<< HEAD
import com.poly.DATN_BookWorms.entities.Detailbookings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailBookingService {
    List<Detailbookings> findByAll();

    Detailbookings  dtb(String id);

    List<Detailbookings> findByBookingId(String bookingId);
=======
import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.response.DetailBookingResponse;

@Service
public interface DetailBookingService {
	List<Books> findTop5Seller();
	
	List<Books> findTop5Inventory();
>>>>>>> zendyy/back_end
}
