package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.DetailBooking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailBookingService {
    List<DetailBooking> findByAll();

    DetailBooking dtb(String id);

    List<DetailBooking> findByBookingId(String bookingId);

}
