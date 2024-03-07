package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.responses.BookRankingToNumberResponse;
import com.poly.DATN_BookWorms.responses.BookRankingToSalesResponse;
import com.poly.DATN_BookWorms.responses.CategoryRanking;
import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repositories.*;
import com.poly.DATN_BookWorms.services.SalesAnalysisService;
import com.poly.DATN_BookWorms.services.ShopService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ISalesAnalysis implements SalesAnalysisService {
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
        List<Booking> bookings = bookingsRepo.getIsSuccess(startDate, endDate);
        List<DetailBooking> detailbookings = new ArrayList<>();

        for (Booking booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailBooking());
        }
        Account user = sessionService.get("user");
        ShopOnline shoponlines = shopService.findUserId(user.getUserid());

        List<DetailBooking> listDetailBookingSales = new ArrayList<>();
        for (DetailBooking detailbooking : detailbookings) {
            if (detailbooking.getBook().getShopid().equals(shoponlines.getShopid())) {
                listDetailBookingSales.add(detailbooking);
            }
        }
        //Tổng giá trị
        double monthSales = 0;
        for (DetailBooking listDetailBooking1 : listDetailBookingSales) {
            monthSales += (listDetailBooking1.getQuantity() * listDetailBooking1.getBook().getPrice()) * 0.9;
        }

        return monthSales;
    }

    @Override
    public int getMonthOrder(Date startDate, Date endDate) {
        List<Booking> bookings = bookingsRepo.getIsPaid(startDate, endDate);
        List<DetailBooking> detailbookings = new ArrayList<>();
        for (Booking booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailBooking());
        }
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());

        List<DetailBooking> listDetailBookingSales = new ArrayList<>();
        for (DetailBooking detailbooking : detailbookings) {
            if (detailbooking.getBook().getShopid().equals(shoponline.getShopid())) {
                listDetailBookingSales.add(detailbooking);
            }
        }
        return listDetailBookingSales.size();
    }

    @Override
    public int getProductView(Integer shopId) {
        List<Book> listBookFormShop = booksRepo.findBookByShopId(shopId);

        if (listBookFormShop.size() > 0)
            return booksRepo.getProductViews(shopId);
        else
            return 0;
    }

    @Override
    public List<CategoryRanking> getCategoryRanking() {
        List<Booking> bookings = bookingsRepo.findAll();
        List<DetailBooking> detailbookings = new ArrayList<>();
        for (Booking booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailBooking());
        }
        System.out.println("detailbooking " + detailbookings.size());
        Account user = sessionService.get("user");
        ShopOnline shoponlines = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Book> books = shoponlines.getListOfBook();
        System.out.println("books " + books.size());

        List<CategoryRanking> categoryRankings = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        for (Book book : books) {
            for (DetailBooking detailbooking : detailbookings) {
                if (detailbooking.getBook().getBookid().equals(book.getBookid())) {
                    List<TypeBook> typebooks = book.getListOfTypeBook();

                    //Lấy category từ sách có trong detailbooking

                    for (TypeBook typebook : typebooks) {
                        categories.add(typebook.getCategory());
                    }

                }
            }
        }
        System.out.println("categories " + categories.size());
        //Thêm vào thống kê tăng số lượng nếu có từ 2 lần trở lên
        for (Category category : categories) {
            int count = 0;
            if (categoryRankings.isEmpty()) {
                categoryRankings.add(new CategoryRanking(category, 1));
            } else {
                for (CategoryRanking categoryRanking : categoryRankings) {
                    count++;
                    if (categoryRanking.getCategory().getCategoryid().equals(category.getCategoryid())) {
                        categoryRanking.setOrderNumbers(categoryRanking.getOrderNumbers() + 1);
                        break;
                    }
                    if (count >= categoryRankings.size()) {
                        categoryRankings.add(new CategoryRanking(category, 1));
                    }


                }
            }

            System.out.println(count);

        }
        System.out.println("categoryRaking " + categoryRankings.size());
        return categoryRankings;
    }

    @Override
    public List<BookRankingToSalesResponse> getBookRankingToSales() {
        //Lấy detail booking
        List<Booking> bookings = bookingsRepo.findAll();
        List<DetailBooking> detailbookings = new ArrayList<>();
        for (Booking booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailBooking());
        }

        //Lấy sách của shop
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Book> books = shoponline.getListOfBook();

        List<BookRankingToSalesResponse> listResponseBookRankingToSales = new ArrayList<>();
        for (Book book : books) {
            for (DetailBooking detailbooking : detailbookings) {
                if (detailbooking.getBook().getBookid().equals(book.getBookid())) {
                    if (listResponseBookRankingToSales.isEmpty()) {
                        listResponseBookRankingToSales.add(new BookRankingToSalesResponse(book, book.getPrice() * detailbooking.getQuantity()));
                        break;
                    }
                    System.out.println(listResponseBookRankingToSales.size());
                    for (int i = 0; i < listResponseBookRankingToSales.size(); i++) {
                        if (listResponseBookRankingToSales.get(i).getBook().getBookid() == book.getBookid()) {
                            listResponseBookRankingToSales.get(i).setSale(listResponseBookRankingToSales.get(i).getSale() + book.getPrice() * detailbooking.getQuantity());
                            break;
                        }
                        if (i >= listResponseBookRankingToSales.size()) {
                            listResponseBookRankingToSales.add(new BookRankingToSalesResponse(book, book.getPrice() * detailbooking.getQuantity()));
                            break;
                        }
                    }
                }
            }
        }


        System.out.println("listBookRankingToSales " + listResponseBookRankingToSales.size());
        return listResponseBookRankingToSales;
    }

    @Override
    public List<BookRankingToNumberResponse> getBookRankingToNumber() {
        List<Booking> bookings = bookingsRepo.findAll();
        List<DetailBooking> detailbookings = new ArrayList<>();
        for (Booking booking : bookings) {
            detailbookings.addAll(booking.getListOfDetailBooking());
        }

        //Lấy sách của shop
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop

        List<Book> books = shoponline.getListOfBook();

        List<BookRankingToNumberResponse> listBookRankingToNumberResponse = new ArrayList<>();
        for (Book book : books) {
            for (DetailBooking detailbooking : detailbookings) {
                if (detailbooking.getBook().getBookid().equals(book.getBookid())) {
                    if (listBookRankingToNumberResponse.isEmpty()) {
                        listBookRankingToNumberResponse.add(new BookRankingToNumberResponse(book, detailbooking.getQuantity()));
                        break;
                    }
                    System.out.println(listBookRankingToNumberResponse.size());
                    for (int i = 0; i < listBookRankingToNumberResponse.size(); i++) {
                        if (listBookRankingToNumberResponse.get(i).getBook().getBookid() == book.getBookid()) {
                            listBookRankingToNumberResponse.get(i).setNumbers(listBookRankingToNumberResponse.get(i).getNumbers() + detailbooking.getQuantity());
                            break;
                        }
                        if (i >= listBookRankingToNumberResponse.size()) {
                            listBookRankingToNumberResponse.add(new BookRankingToNumberResponse(book, detailbooking.getQuantity()));
                            break;
                        }
                    }
                }
            }
        }


        System.out.println("listBookRankingToSales " + listBookRankingToNumberResponse.size());
        return listBookRankingToNumberResponse;
    }

    @Override
    public List<Book> getBookRankingToView() {
        //Lấy sách của shop
        Account user = sessionService.get("user");
        ShopOnline shoponline = shopService.findUserId(user.getUserid());
        // Lấy list sách của Shop
        List<Book> book = booksRepo.findByAccordingViewAndShopId(shoponline.getShopid());
        return book;
    }
}
