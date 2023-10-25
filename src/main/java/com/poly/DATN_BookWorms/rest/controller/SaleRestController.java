package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
