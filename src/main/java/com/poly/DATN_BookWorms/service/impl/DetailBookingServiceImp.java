package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.service.DetailBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailBookingServiceImp implements DetailBookingService {

    @Autowired
    DetailbookingsRepo detailbookingsRepo;
    @Override
    public List<Detailbookings> findByAll() {

        return detailbookingsRepo.findAll();
    }

    @Override
    public Detailbookings dtb(String id) {
        return detailbookingsRepo.findById(id).get();
    }

    @Override
    public List<Detailbookings> findByBookingId(String bookingId) {
        return detailbookingsRepo.findByBookingId(bookingId);
    }
}
