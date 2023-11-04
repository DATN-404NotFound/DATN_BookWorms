package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.service.AccountAddressService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/address")
public class AccountAddressRestController {

	@Autowired
	CRC32_SHA256 crc32_SHA256;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AccountAddressService accountAddressService;
	
	@GetMapping
	public List<Addressusers> getAddressByUser(){ 
		String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
		System.out.println("in ra "+accountAddressService.getAdressByUser(userid) );
		return accountAddressService.getAdressByUser(userid);
	}
}
