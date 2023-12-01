package com.poly.DATN_BookWorms.repo;




import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Bookings;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Shoponlines;

public interface BookingsRepo extends JpaRepository<Bookings, String>{
    @Query("SELECT o FROM Bookings o WHERE o.createat >= :startDate AND o.createat <= :endDate AND o.orderstatuses.orderstatusid = 5")
    List<Bookings> getIsSuccess(Date startDate, Date endDate);

	@Query("SELECT o FROM Bookings o WHERE o.createat >= :startDate AND o.createat <= :endDate AND o.orderstatuses.orderstatusid = 2")
	List<Bookings> getIsPaid(Date startDate, Date endDate);

//	@Query("SELECT o FROM Bookings o WHERE o.account.username = ?1")
//	List<Bookings> findByUsername(String username);
	

	@Query("Select b from Bookings b where b.account.userid like ?1")
	List<Bookings> findBookingByUser(String userid);
	
	@Query("Select b.shoponlines from Books b where b.bookid in (Select c.books.bookid from Cart c where c.account.userid like ?1 )")
	List<Shoponlines> list_cart_shopId(String userid);

	@Query("Select b from Bookings b where b.orderstatusid = ?1")
	List<Bookings> ListBookings_Status(String orderStatusId);


	List<Bookings> findByuserid(String userId);
}
