package com.poly.DATN_BookWorms.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.OutputStream;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import com.itextpdf.text.DocumentException;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.entities.Publishingcompanies;
import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.BookingsRepo;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.response.DetailBookingResponse;
import com.poly.DATN_BookWorms.service.BookService;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;
import com.poly.DATN_BookWorms.service.SaleService;
import com.poly.DATN_BookWorms.service.ShopOnlineService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
		List<Bookings> item = bookingService.findAll();
		model.addAttribute("item", item);
		long countBooking = bookingService.countBooking();

		model.addAttribute("countBooking", countBooking);
		display(model);
		return "admin/findOrderUser";
	}
	
	@GetMapping("/statisticalbook")
	public String statisticalBook(Model model) {
		List<Books> item = bookService.findAll();
		model.addAttribute("item", item);
		return "admin/statisticalbook";
	}

	@GetMapping("/statisticalshop")
	public String statisticalShop(Model model) {
		List<Shoponlines> item = shopOnlineService.findAll();
		model.addAttribute("item", item);
		return "admin/statisticalshop";
	}
	
	@GetMapping("/statisticalpublishingcompany")
	public String statisticalPublishingCompany(Model model) {
		List<Publishingcompanies> item = publishingCompanyService.findAll();
		model.addAttribute("item", item);
		return "admin/statisticalpublishingcompany";
	}

	@GetMapping("/unpaid")
	public String unpaid(Model model) {
		List<Bookings> item = bookingService.unpaid();
		model.addAttribute("item", item);
		display(model);
		return "admin/unpaid";
	}

	@GetMapping("/paid")
	public String paid(Model model) {
		List<Bookings> item = bookingService.unpaid();
		model.addAttribute("item", item);
		display(model);
		return "admin/paid";
	}

	@GetMapping("/confirm")
	public String confirm(Model model) {
		List<Bookings> item = bookingService.confirm();
		display(model);
		model.addAttribute("item", item);
		return "admin/confirm";
	}

	@GetMapping("/delivering")
	public String delivering(Model model) {
		List<Bookings> item = bookingService.delivering();
		model.addAttribute("item", item);
		display(model);
		return "admin/delivering";
	}

	@GetMapping("/processed")
	public String processed(Model model) {
		List<Bookings> item = bookingService.processed();
		model.addAttribute("item", item);
		display(model);
		return "admin/processed";
	}

	@GetMapping("/cancel")
	public String cancel(Model model) {
		List<Bookings> item = bookingService.cancel();
		model.addAttribute("item", item);
		display(model);
		return "admin/cancel";
	}

	@GetMapping("/refund")
	public String refund(Model model) {
		List<Bookings> item = bookingService.refund();
		model.addAttribute("item", item);
		display(model);
		return "admin/refund";
	}

	@GetMapping("/findtop5")
	public String findTop5ByOrderSoldDesc(Model model) {
		List<Books> item = booksRepo.findTop5Seller();
		model.addAttribute("item", item);

		List<Books> item1 = booksRepo.findTop5Inventory();
		model.addAttribute("item1", item1);

		return "admin/findtop5";
	}

	@GetMapping("/listvoucher")
	public String listVoucher(Model model) {
		List<Sales> item = saleService.findAll();
		model.addAttribute("item", item);
		return "admin/listvoucher";
	}
}
