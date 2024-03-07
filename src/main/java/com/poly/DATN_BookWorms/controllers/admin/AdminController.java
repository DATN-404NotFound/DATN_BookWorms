package com.poly.DATN_BookWorms.controllers.admin;

import java.util.List;

import com.poly.DATN_BookWorms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DATN_BookWorms.repositories.BookingsRepo;
import com.poly.DATN_BookWorms.repositories.BooksRepo;
import com.poly.DATN_BookWorms.repositories.ViewWebRepo;
import com.poly.DATN_BookWorms.services.BookService;
import com.poly.DATN_BookWorms.services.BookingService;
import com.poly.DATN_BookWorms.services.PublishingCompanyService;
import com.poly.DATN_BookWorms.services.SaleService;
import com.poly.DATN_BookWorms.services.ShopOnlineService;
import com.poly.DATN_BookWorms.utils.SessionService;

import org.thymeleaf.TemplateEngine;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BookingService bookingService;
    @Autowired
    BookService bookService;
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    SaleService saleService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    BookingsRepo bookingsRepo;
    @Autowired
    ShopOnlineService shopOnlineService;
    @Autowired
    PublishingCompanyService publishingCompanyService;
    @Autowired
    ViewWebRepo viewWebRepo;

    @Autowired
    SessionService session;

    public void display(Model model) {
        long countUnpaid = bookingService.countUnpaid();
        model.addAttribute("countUnpaid", countUnpaid);

        long countPaid = bookingService.countPaid();
        model.addAttribute("countPaid", countPaid);

        long countConfirm = bookingService.countConfirm();
        model.addAttribute("countConfirm", countConfirm);

        long countDelivering = bookingService.countDelivering();
        model.addAttribute("countDelivering", countDelivering);

        long countProcessed = bookingService.countProcessed();
        model.addAttribute("countProcessed", countProcessed);

        long countCancel = bookingService.countCancel();
        model.addAttribute("countCancel", countCancel);

        long countRefund = bookingService.countRefund();
        model.addAttribute("countRefund", countRefund);
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "admin/index";
    }

    @GetMapping("/findOrderUser")
    public String findByOrderUser(Model model) {
        List<Booking> item = bookingService.findAll();
        model.addAttribute("item", item);

        long countBooking = bookingService.countBooking();
        ViewWeb viewWeb = viewWebRepo.findById(1).get();

        model.addAttribute("countBooking", countBooking);

        model.addAttribute("view", viewWeb);
        display(model);
        session.set("viewWeb", viewWeb);
        return "admin/findOrderUser";
    }

    @GetMapping("/statisticalbook")
    public String statisticalBook(Model model) {
        List<Book> item = bookService.findAll();
        model.addAttribute("item", item);
        return "statisticalBook";
    }

    @GetMapping("/statisticalshop")
    public String statisticalShop(Model model) {
        List<ShopOnline> item = shopOnlineService.findAll();
        model.addAttribute("item", item);
        return "statisticalShop";
    }

    @GetMapping("/statisticalpublishingcompany")
    public String statisticalPublishingCompany(Model model) {
        List<PublishingCompany> item = publishingCompanyService.findAll();
        model.addAttribute("item", item);
        return "statisticalPublishingCompany";
    }

    @GetMapping("/unpaid")
    public String unpaid(Model model) {
        List<Booking> item = bookingService.unpaid();
        model.addAttribute("item", item);
        display(model);
        return "admin/unpaid";
    }

    @GetMapping("/paid")
    public String paid(Model model) {
        List<Booking> item = bookingService.unpaid();
        model.addAttribute("item", item);
        display(model);
        return "admin/paid";
    }

    @GetMapping("/confirm")
    public String confirm(Model model) {
        List<Booking> item = bookingService.confirm();
        display(model);
        model.addAttribute("item", item);
        return "admin/confirm";
    }

    @GetMapping("/delivering")
    public String delivering(Model model) {
        List<Booking> item = bookingService.delivering();
        model.addAttribute("item", item);
        display(model);
        return "admin/delivering";
    }

    @GetMapping("/processed")
    public String processed(Model model) {
        List<Booking> item = bookingService.processed();
        model.addAttribute("item", item);
        display(model);
        return "admin/processed";
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        List<Booking> item = bookingService.cancel();
        model.addAttribute("item", item);
        display(model);
        return "admin/cancel";
    }

    @GetMapping("/refund")
    public String refund(Model model) {
        List<Booking> item = bookingService.refund();
        model.addAttribute("item", item);
        display(model);
        return "admin/refund";
    }

    @GetMapping("/findtop5")
    public String findTop5ByOrderSoldDesc(Model model) {
        List<Book> item = booksRepo.findTop5Seller();
        model.addAttribute("item", item);

        List<Book> item1 = booksRepo.findTop5Inventory();
        model.addAttribute("item1", item1);

        return "findTop5";
    }

    @GetMapping("/listvoucher")
    public String listVoucher(Model model) {
        List<Sale> item = saleService.findAll();
        model.addAttribute("item", item);
        return "listVoucher";
    }
}
