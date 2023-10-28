package com.poly.DATN_BookWorms.repo;



import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Bookings;

public interface BookingsRepo extends JpaRepository<Bookings, String>{

//	@Query("SELECT o FROM Bookings o WHERE o.account.username = ?1")
//	List<Bookings> findByUsername(String username);
}
