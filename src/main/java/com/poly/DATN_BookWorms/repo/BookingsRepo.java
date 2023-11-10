package com.poly.DATN_BookWorms.repo;




import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Bookings;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingsRepo extends JpaRepository<Bookings, String>{
    @Query("SELECT o FROM Bookings o WHERE o.createat >= :startDate AND o.createat <= :endDate AND o.orderstatuses.orderstatusid = 5")
    List<Bookings> getIsPaid(Date startDate, Date endDate);

}
