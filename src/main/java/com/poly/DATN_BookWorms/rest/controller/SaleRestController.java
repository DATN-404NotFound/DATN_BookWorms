package com.poly.DATN_BookWorms.rest.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.service.SaleService;

@RestController
@CrossOrigin("")
@RequestMapping("/rest/sale")
public class SaleRestController {

	@Autowired
	SaleService saleService;
	
	@GetMapping("/")
	public List<Sales> getSale(){ 
		return saleService.findAll();
	}
	
	@GetMapping("/{coupon}")
	public Sales getSaleCounpon(@PathVariable("coupon") String coupon){ 
		return saleService.findById(coupon);
	}
	
	@GetMapping("/{intendfor}")
	public List<Sales> getSaleShopOfIntend(@PathVariable("intendfor") String intendfor){ 
		return saleService.saleOfShopIntendFor(intendfor);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createSales(
			@RequestParam String promotionname,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date createat,
			@RequestParam String descriptions,
			@RequestParam BigDecimal discountpercentage,
			@RequestParam String statuses,
			@RequestParam String intendfor) {

//		Sales createdSale = saleService.create(promotionname, createat, descriptions, discountpercentage, statuses, intendfor);
Sales createdSale = saleService.create(promotionname, createat, descriptions, discountpercentage, statuses, intendfor);
		if (createdSale != null) {
			return ResponseEntity.ok("Sale created successfully. Coupon code: " + createdSale.getCouoponcode());
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create sale.");
		}
	}

}
