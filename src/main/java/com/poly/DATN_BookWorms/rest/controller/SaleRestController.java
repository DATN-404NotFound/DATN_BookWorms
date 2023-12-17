package com.poly.DATN_BookWorms.rest.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Hassales;
import com.poly.DATN_BookWorms.service.HasSaleService;
import com.poly.DATN_BookWorms.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.service.SaleService;

@RestController
@CrossOrigin("")
@RequestMapping("/rest/sale")
public class SaleRestController {
@Autowired
	SessionService service;
	@Autowired
	SaleService saleService;
	@Autowired
	HasSaleService hasSaleService;
	
	@GetMapping("/")
	public List<Sales> getSale(){ 
		return saleService.findAll();
	}
	
	@GetMapping("/{coupon}")
	public Sales getSaleCounpon(@PathVariable("coupon") String coupon){ 
		return saleService.findById(coupon);
	}

	@GetMapping("/getListHasSales")
	public ResponseEntity<List<Hassales>> getListHasSales(){
		return ResponseEntity.ok(hasSaleService.findAll());
	}

	@PostMapping("/getHasSaleFromBook")
	public ResponseEntity<Integer> getHasSaleFromBoook(@RequestParam("bookid") String bookId, @RequestParam("saleid") String saleId){
		Integer hasSalesId = hasSaleService.findHasSaleIdByBookId(Integer.parseInt(bookId), saleId);
		if(hasSalesId == null)
			return ResponseEntity.ok(null);
		else
			return ResponseEntity.ok(hasSalesId);
	}
	@GetMapping("/{intendfor}")
	public List<Sales> getSaleShopOfIntend(@PathVariable("intendfor") String intendfor){ 
		return saleService.saleOfShopIntendFor(intendfor);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
		Sales createdSale = saleService.create(sales);
		return ResponseEntity.ok(createdSale);
	}
	@GetMapping("/listvoucher/{intendforv}")
	public List<Sales> getAllVoucher( @PathVariable("intendforv") String intendfor) {
		Account account = service.get("user");
		return saleService.findAllByShopid(intendfor,account.getListOfShoponlines().get(0).getShopid());
	}
	@GetMapping("/findByCouponCode/{couponCode}")
	public List<Hassales> findAllByCouponCode(@PathVariable("couponCode") String couponCode) {
		return hasSaleService.findAllByCouponCode(couponCode);
	}
	@PostMapping(value = "/createHassale/{bookID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hassales> createHassales(@RequestBody Hassales hassales, @PathVariable("bookID") Integer bookID) {

		Hassales createdHassale = new Hassales();
		createdHassale.setBookid(bookID);
		createdHassale.setSaleid(hassales.getSaleid());
		createdHassale.setStarttime(new Date());
		createdHassale.setEndtime(hassales.getEndtime());
		hasSaleService.saveHassales(createdHassale);
		return ResponseEntity.ok(createdHassale);
	}
	@DeleteMapping("/deleteHassale/{hassaleId}")
	public ResponseEntity<String> deleteHassalesById(@PathVariable Integer hassaleId) {
		try {
			hasSaleService.deleteHassalesById(hassaleId);
			return new ResponseEntity<>("Hassales deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error deleting Hassales: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/findAllBySaleId/{saleId}")
	public ResponseEntity<List<Hassales>> findAllBySaleId(@PathVariable String saleId) {
		List<Hassales> hassalesList = hasSaleService.findAllBysaleid(saleId);
		if (!hassalesList.isEmpty()) {
			return new ResponseEntity<>(hassalesList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
