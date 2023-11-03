package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Discountcodes;
import com.poly.DATN_BookWorms.service.DiscountCodeService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/discount")
public class DiscountCodeRestController {

	@Autowired
	DiscountCodeService discountCodeService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	@GetMapping("/{saleid}")
	public Discountcodes getDiscountCodeBySaleid(@PathVariable("saleid") String saleid) { 
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		System.out.println("in : "+   discountCodeService.findSalesId(saleid, userid));
		return discountCodeService.findSalesId(saleid, userid);
	}
	
	@GetMapping
	public List<Discountcodes> findAll(){ 
		return  discountCodeService.findAll();
	}
	
}
