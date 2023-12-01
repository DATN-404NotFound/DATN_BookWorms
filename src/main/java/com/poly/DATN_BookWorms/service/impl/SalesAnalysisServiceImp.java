package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.BookingsRepo;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.repo.OrderstatusesRepo;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;
import com.poly.DATN_BookWorms.service.SalesAnalysisService;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesAnalysisServiceImp implements SalesAnalysisService {
//    @Autowired
//    BookingsRepo bookingsRepo;
//    @Autowired
//    SessionService sessionService;
//    @Autowired
//    ShopService shopService;
//    @Autowired
//    BooksRepo booksRepo;
//    @Override
//    public double getMonthSales(Date startDate, Date endDate) {
//        List<Bookings> bookings = bookingsRepo.getIsSuccess(startDate, endDate);
//        List<Detailbookings> detailbookings = new ArrayList<>();
//        for (Bookings booking: bookings) {
//               detailbookings.addAll(booking.getListOfDetailbookings());
//        }
//        Account user = sessionService.get("user");
//        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
//
//        List<Detailbookings> listDetailBookingSales = new ArrayList<>();
//        for (Detailbookings detailbooking: detailbookings) {
//            if (detailbooking.getBooks().getShopid().equals(shoponlines.getShopid())){
//                listDetailBookingSales.add(detailbooking);
//            }
//        }
//        //Tổng giá trị
//        double monthSales = 0;
//        for (Detailbookings listDetailbookings1: listDetailBookingSales) {
//           monthSales+=(listDetailbookings1.getQuantity() * listDetailbookings1.getBooks().getPrice()) * 0.9;
//        }
//
//        return monthSales;
//    }

//    @Override
//    public int getMonthOrder(Date startDate, Date endDate) {
//        List<Bookings> bookings = bookingsRepo.getIsPaid(startDate, endDate);
//        List<Detailbookings> detailbookings = new ArrayList<>();
//        for (Bookings booking: bookings) {
//            detailbookings.addAll(booking.getListOfDetailbookings());
//        }
//        Account user = sessionService.get("user");
//        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
//
//        List<Detailbookings> listDetailBookingSales = new ArrayList<>();
//        for (Detailbookings detailbooking: detailbookings) {
//            if (detailbooking.getBooks().getShopid().equals(shoponlines.getShopid())){
//                listDetailBookingSales.add(detailbooking);
//            }
//        }
//        return listDetailBookingSales.size();
//    }
//
//    @Override
//    public int getProductView(Integer shopId) {
//
//        return booksRepo.getProductViews(shopId);
//    }

}
