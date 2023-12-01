package com.poly.DATN_BookWorms.repo;

import java.util.List;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import org.springframework.data.jpa.repository.Query;

public interface DetailbookingsRepo extends JpaRepository<Detailbookings, String>{
    @Query("select db from Detailbookings db where db.bookingid = ?1")
    List<Detailbookings> findByBookingId(String bookingId);
=======

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.response.DetailBookingResponse;

public interface DetailbookingsRepo extends JpaRepository<Detailbookings, Integer>{
	
>>>>>>> zendyy/back_end
}
