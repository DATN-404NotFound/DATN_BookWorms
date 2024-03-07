package com.poly.DATN_BookWorms.restControllers;

import com.poly.DATN_BookWorms.entities.DetailBooking;
import com.poly.DATN_BookWorms.services.DetailBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/detailBooking")
public class DetailBookingRestController {
    @Autowired
    DetailBookingService detailBookingService;


    @GetMapping
    public List<DetailBooking> detailbooking(){

        return detailBookingService.findByAll();
    }

    @GetMapping("/{id}")
    public DetailBooking detailbooking(@PathVariable("id")  String a){
        return detailBookingService.dtb(a);
    }

    @GetMapping("/2/{bookingid}")
    public List<DetailBooking> detailbooking1(@PathVariable("bookingid")  String a){
        return detailBookingService.findByBookingId(a);
    }

}
