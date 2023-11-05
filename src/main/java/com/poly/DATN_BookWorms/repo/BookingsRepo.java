package com.poly.DATN_BookWorms.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Shoponlines;

public interface BookingsRepo extends JpaRepository<Bookings, String>{

//	@Query("SELECT o FROM Bookings o WHERE o.account.username = ?1")
//	List<Bookings> findByUsername(String username);
	

	@Query("Select b from Bookings b where b.account.userid like ?1")
	List<Bookings> findBookingByUser(String userid);
	
	@Query("Select b.shoponlines from Books b where b.bookid in (Select c.books.bookid from Cart c where c.account.userid like ?1 )")
	List<Shoponlines> list_cart_shopId(String userid);
	
}
