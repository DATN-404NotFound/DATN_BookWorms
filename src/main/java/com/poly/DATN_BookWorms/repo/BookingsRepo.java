package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Bookings;


public interface BookingsRepo extends JpaRepository<Bookings, String>{

//	@Query("SELECT o FROM Bookings o WHERE o.account.username = ?1")
//	List<Bookings> findByUsername(String username);
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 1")
    long countUnpaid();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 2")
    long countPaid();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 3")
    long countConfirm();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 4")
    long countDelivering();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 5")
    long countProcessed();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 6")
    long countCancel();
	
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.orderstatuses.orderstatusid = 7")
    long countRefund();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 1")
	List<Bookings> unpaid();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 2")
	List<Bookings> paid();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 3")
	List<Bookings> confirm();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 4")
	List<Bookings> delivering();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 5")
	List<Bookings> processed();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 6")
	List<Bookings> cancel();
	
	@Query("SELECT b FROM Bookings b WHERE b.orderstatuses.orderstatusid = 7")
	List<Bookings> refund();
}
