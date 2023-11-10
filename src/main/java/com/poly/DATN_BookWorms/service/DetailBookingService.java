package com.poly.DATN_BookWorms.service;

import com.poly.DATN_BookWorms.entities.Detailbookings;

import java.util.List;

public interface DetailBookingService {
    List<Detailbookings> findByAll();

    Detailbookings  dtb(String id);

    List<Detailbookings> findByBookingId(String bookingId);
}
