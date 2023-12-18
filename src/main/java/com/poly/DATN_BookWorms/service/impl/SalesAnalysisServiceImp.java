package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.beans.BookRankingToNumber;
import com.poly.DATN_BookWorms.beans.BookRankingToSales;
import com.poly.DATN_BookWorms.beans.CategoryRanking;
import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repo.*;
import com.poly.DATN_BookWorms.service.SalesAnalysisService;
import com.poly.DATN_BookWorms.service.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesAnalysisServiceImp implements SalesAnalysisService {
    @Autowired
    BookingsRepo bookingsRepo;
    @Autowired
    SessionService sessionService;
    @Autowired
    ShopService shopService;
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    CategoriesRepo categoriesRepo;
    @Autowired
    DetailbookingsRepo detailbookingsRepo;

    @Override
    public double getMonthSales(Date startDate, Date endDate) {
        List<Bookings> bookings = bookingsRepo.getIsSuccess(startDate, endDate);
        List<Detailbookings> detailbookings = new ArrayList<>();

        for (Bookings booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailbookings());
        }
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());

        List<Detailbookings> listDetailBookingSales = new ArrayList<>();
        for (Detailbookings detailbooking : detailbookings) {
            if (detailbooking.getBooks().getShopid().equals(shoponlines.getShopid())) {
                listDetailBookingSales.add(detailbooking);
            }
        }
        //Tổng giá trị
        double monthSales = 0;
        for (Detailbookings listDetailbookings1 : listDetailBookingSales) {
            monthSales += (listDetailbookings1.getQuantity() * listDetailbookings1.getBooks().getPrice()) * 0.9;
        }

        return monthSales;
    }

    @Override
    public int getMonthOrder(Date startDate, Date endDate) {
        List<Bookings> bookings = bookingsRepo.getIsPaid(startDate, endDate);
        List<Detailbookings> detailbookings = new ArrayList<>();
        for (Bookings booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailbookings());
        }
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());

        List<Detailbookings> listDetailBookingSales = new ArrayList<>();
        for (Detailbookings detailbooking : detailbookings) {
            if (detailbooking.getBooks().getShopid().equals(shoponlines.getShopid())) {
                listDetailBookingSales.add(detailbooking);
            }
        }
        return listDetailBookingSales.size();
    }

    @Override
    public int getProductView(Integer shopId) {
        List<Books> listBookFormShop = booksRepo.findBookByShopId(shopId);

        if (listBookFormShop.size() > 0)
            return booksRepo.getProductViews(shopId);
        else
            return 0;
    }

    @Override
    public List<CategoryRanking> getCategoryRanking() {
        Account user = sessionService.get("user");
        Shoponlines shoponlines = shopService.findUserId(user.getUserid());
        Map<Integer, Integer> categoryRankings = detailbookingsRepo.findCateRankByShopId(shoponlines.getShopid());
        List<CategoryRanking> categoryRankings1 = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : categoryRankings.entrySet()) {
            Optional<Categories> categories = categoriesRepo.findById(entry.getKey());

           categoryRankings1.add(new CategoryRanking(categories, entry.getValue()));
        }
        return categoryRankings1;
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
                    if (listBookRankingToSales.isEmpty()) {
                        listBookRankingToSales.add(new BookRankingToSales(book, book.getPrice() * detailbooking.getQuantity()));
                        break;
                    }
                    System.out.println(listBookRankingToSales.size());
                    for (int i = 0; i < listBookRankingToSales.size(); i++) {
                        if (listBookRankingToSales.get(i).getBook().getBookid() == book.getBookid()) {
                            listBookRankingToSales.get(i).setSale(listBookRankingToSales.get(i).getSale() + book.getPrice() * detailbooking.getQuantity());
                            break;
                        }
                        if (i >= listBookRankingToSales.size()) {
                            listBookRankingToSales.add(new BookRankingToSales(book, book.getPrice() * detailbooking.getQuantity()));
                            break;
                        }
                    }
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
                    if (listBookRankingToNumber.isEmpty()) {
                        listBookRankingToNumber.add(new BookRankingToNumber(book, detailbooking.getQuantity()));
                        break;
                    }
                    System.out.println(listBookRankingToNumber.size());
                    for (int i = 0; i < listBookRankingToNumber.size(); i++) {
                        if (listBookRankingToNumber.get(i).getBook().getBookid() == book.getBookid()) {
                            listBookRankingToNumber.get(i).setNumbers(listBookRankingToNumber.get(i).getNumbers() + detailbooking.getQuantity());
                            break;
                        }
                        if (i >= listBookRankingToNumber.size()) {
                            listBookRankingToNumber.add(new BookRankingToNumber(book, detailbooking.getQuantity()));
                            break;
                        }
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
