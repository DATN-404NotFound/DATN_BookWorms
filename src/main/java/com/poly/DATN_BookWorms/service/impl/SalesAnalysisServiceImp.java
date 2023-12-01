package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.beans.BookRankingToNumber;
import com.poly.DATN_BookWorms.beans.BookRankingToSales;
import com.poly.DATN_BookWorms.beans.CategoryRanking;
import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.*;
import com.poly.DATN_BookWorms.service.SalesAnalysisService;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Override
    public List<CategoryRanking> getCategoryRanking() {
    	  logger.info("getCategoryRanking start......");
        List<Bookings> bookings = bookingsRepo.findAll();
        logger.info("bookings list size : {}", bookings.size());
       try {
    	   if(bookings.size() ==0) { 
           	logger.info("get list bookings is null");
        
           }
           List<Detailbookings> detailbookings = new ArrayList<>();
           for (Bookings booking : bookings) {
           	 logger.info("booking item : {}", bookings.toString());
               detailbookings.addAll(booking.getListOfDetailbookings());
           }
           System.out.println("detailbooking " + detailbookings.size());
           Account user = sessionService.get("user");
           logger.info("Account item : {}", user.toString());
           Shoponlines shoponlines = shopService.findUserId(user.getUserid());
           logger.info("Acshoponlinescount item : {}", shoponlines.toString());
           // Lấy list sách của Shop

           List<Books> books = shoponlines.getListOfBooks();
           logger.info("get list books size : {}",books.size());
           System.out.println("books " + books.size());

           List<CategoryRanking> categoryRankings = new ArrayList<>();
           logger.info("get list CategoryRanking size : {}",categoryRankings.size());
           List<Categories> categories = new ArrayList<>();
           for (Books book : books) {
               for (Detailbookings detailbooking : detailbookings) {
               	logger.info("detailbooking : {}",detailbooking.toString());
                   if (detailbooking.getBooks().getBookid().equals(book.getBookid())) {
                       List<Typebooks> typebooks = book.getListOfTypebooks();
                       for (Typebooks typebook : typebooks) {
                       	logger.info("typebook : {}",typebook.toString());
                           //categories.add(typebook.getCategories());
                       }

                   }
               }
           }
           System.out.println("categories " + categories.size());
           //Thêm vào thống kê tăng số lượng nếu có từ 2 lần trở lên
           for (Categories category : categories) {
           	logger.info("category : {}",category.toString());
               int count = 0;
               if (categoryRankings.isEmpty()) {
                  // categoryRankings.add(new CategoryRanking(category, 1));
               } else {
                   for (CategoryRanking categoryRanking : categoryRankings) {
                   	logger.info("categoryRanking : {}",categoryRanking.toString());
                       count++;
//                       if (categoryRanking.getCategories().getCategoryid().equals(category.getCategoryid())) {
//                           categoryRanking.setOrderNumbers(categoryRanking.getOrderNumbers() + 1);
//                           break;
//                       }
//                       if (count >= categoryRankings.size()) {
//                           categoryRankings.add(new CategoryRanking(category, 1));
//                       }


                   }
               }

               System.out.println(count);

           }
           System.out.println("categoryRaking " + categoryRankings.size());
           return categoryRankings;
	} catch (Exception e) {
		logger.error("Error during getCategoryRanking with error : {}", e);
		return null;
		// TODO: handle exception
	}
    }

    @Override
    public List<BookRankingToSales> getBookRankingToSales() {
        //Lấy detail booking
        List<Bookings> bookings = bookingsRepo.findAll();
        List<Detailbookings> detailbookings = new ArrayList<>();
        for (Bookings booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailbookings());
        }

        //Lấy sách của shop
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Books> books = shoponlines.getListOfBooks();

        List<BookRankingToSales> listBookRankingToSales = new ArrayList<>();
        for (Books book : books) {
            for (Detailbookings detailbooking : detailbookings) {
                if (detailbooking.getBooks().getBookid().equals(book.getBookid())) {
//                    if (listBookRankingToSales.isEmpty()) {
//                        listBookRankingToSales.add(new BookRankingToSales(book, book.getPrice() * detailbooking.getQuantity()));
//                        break;
//                    }
                    System.out.println(listBookRankingToSales.size());
//                    for (int i = 0; i < listBookRankingToSales.size(); i++) {
//                        if (listBookRankingToSales.get(i).getBook().getBookid() == book.getBookid()){
//                            listBookRankingToSales.get(i).setSale(listBookRankingToSales.get(i).getSale()+ book.getPrice()*detailbooking.getQuantity());
//                            break;
//                        }
//                        if (i>= listBookRankingToSales.size()){
//                            listBookRankingToSales.add(new BookRankingToSales(book, book.getPrice() * detailbooking.getQuantity()));
//                            break;
//                        }
//                    }
                }
            }
        }


        System.out.println("listBookRankingToSales " + listBookRankingToSales.size());
        return listBookRankingToSales;
    }

    @Override
    public List<BookRankingToNumber> getBookRankingToNumber() {
        List<Bookings> bookings = bookingsRepo.findAll();
        List<Detailbookings> detailbookings = new ArrayList<>();
        for (Bookings booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailbookings());
        }

        //Lấy sách của shop
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Books> books = shoponlines.getListOfBooks();

        List<BookRankingToNumber> listBookRankingToNumber = new ArrayList<>();
        for (Books book : books) {
            for (Detailbookings detailbooking : detailbookings) {
                if (detailbooking.getBooks().getBookid().equals(book.getBookid())) {
//                    if (listBookRankingToNumber.isEmpty()) {
//                        listBookRankingToNumber.add(new BookRankingToNumber(book, detailbooking.getQuantity()));
//                        break;
//                    }
                    System.out.println(listBookRankingToNumber.size());
                    for (int i = 0; i < listBookRankingToNumber.size(); i++) {
//                        if (listBookRankingToNumber.get(i).getBook().getBookid() == book.getBookid()){
//                            listBookRankingToNumber.get(i).setNumbers(listBookRankingToNumber.get(i).getNumbers()+ detailbooking.getQuantity());
//                            break;
//                        }
//                        if (i>= listBookRankingToNumber.size()){
//                            listBookRankingToNumber.add(new BookRankingToNumber(book, detailbooking.getQuantity()));
//                            break;
//                        }
                    }
                }
            }
        }


        System.out.println("listBookRankingToSales " + listBookRankingToNumber.size());
        return listBookRankingToNumber;
    }

    @Override
    public List<Books> getBookRankingToView() {
        //Lấy sách của shop
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Books> books = shoponlines.getListOfBooks();
        return books;
    }

}
