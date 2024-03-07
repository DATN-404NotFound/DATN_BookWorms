package com.poly.DATN_BookWorms.restControllers;


import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Booking;
import com.poly.DATN_BookWorms.services.BookingService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/tranportChannel")
public class TranportChannelRestController {
    @Autowired
    BookingService bookingService;
    @Autowired
    SessionService service;

    @GetMapping("/{orderstatusid}")
    public List<Booking> findByOrderstatusid(@PathVariable Integer orderstatusid) {
        Account account = service.get("user");
        return bookingService.findBookingsByShopIdAndOrderStatusID(account.getListOfShopOnline().get(0).getShopid(), orderstatusid);
    }

    @GetMapping("/all")
    public List<Booking> findAll() {
        Account account = service.get("user");
        return bookingService.findBookingsByShopId(account.getListOfShopOnline().get(0).getShopid());
    }

    @PutMapping("/{bookingId}/{status}/updateOrderStatus")
    public void updateOrderStatus(@PathVariable String bookingId, @PathVariable Integer status) {
        bookingService.updateOrderStatus(bookingId, status);
    }


}
