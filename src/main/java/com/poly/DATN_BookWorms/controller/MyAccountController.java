package com.poly.DATN_BookWorms.controller;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.service.AddressService;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.DetailBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/myAccount")
public class MyAccountController {


    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    CRC32_SHA256 crc;
    @Autowired
    BookingService bookingService;
    @Autowired
    HttpServletRequest req;

    @Autowired
    CRC32_SHA256 crc32_SHA256;

    @Autowired
    SessionService service;

    @Autowired
    DetailBookingService detailBookingService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/myPersonal")
    public String  myPersonal(Model model){


        Account account = service.get("user");
        System.out.println("Username:" + account.toString());
        model.addAttribute("account", account);
        return "Client/My_account/MyPersonal";
    }


    @RequestMapping("/address")
    public String  address(Model model){

        Account account = service.get("user");
        model.addAttribute("account", account);
        List<Addressusers> ad = addressService.findByUserId(account.getUserid());
        model.addAttribute("ad", ad);
        return "Client/My_account/Address";
    }

    @RequestMapping("/changePassword")
    public String  changePassword(Model model){
        Account account = service.get("user");
        System.out.println("Username:" + account.toString());
        model.addAttribute("account", account);
        return "Client/My_account/ChangePassword";
    }

    @RequestMapping("/orderMyAccount")
    public String  orderMyAccount(Model model){
        Account account = service.get("user");
        model.addAttribute("account", account);
        List<Bookings> booking = bookingService.findByUserId(account.getUserid());
        model.addAttribute("booking", booking);
//        List<Bookings> booking_StatusId = bookingService.findByStatusId(tab);
//        model.addAttribute("bs", booking_StatusId);
        return "Client/My_account/Order";
    }

    @RequestMapping("/orderDetailMyAccount/{id}")
    public String  orderDetailMyAccount(Model model, @PathVariable("id") String dbId){
        Account account = service.get("user");
        model.addAttribute("account", account);
        List<Detailbookings> a = detailBookingService.findByBookingId(dbId);
        System.out.println("aaaaa" +a.get(0).bookings.toString());
        model.addAttribute("adr", a.get(0).bookings.listOfPayments.get(0));

        model.addAttribute("db", a);
        return "Client/My_account/OrderDetail";
    }
}
