package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.DetailBooking;
import com.poly.DATN_BookWorms.repositories.DetailbookingsRepo;
import com.poly.DATN_BookWorms.services.DetailBookingService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDetailBooking implements DetailBookingService {

	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    DetailbookingsRepo detailbookingsRepo;
    @Override
    public List<DetailBooking> findByAll() {

        return detailbookingsRepo.findAll();
    }

    @Override
    public DetailBooking dtb(String id) {
    	logger.info("get DetalBooking with id : {}",id);
        return detailbookingsRepo.findById(id).get();
    }

    @Override
    public List<DetailBooking> findByBookingId(String bookingId) {
    	logger.info("get DetalBooking with bookingId : {}",bookingId);
        return detailbookingsRepo.findByBookingId(bookingId);
    }
}
