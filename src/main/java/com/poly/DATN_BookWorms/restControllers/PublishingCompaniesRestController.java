package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.PublishingCompany;
import com.poly.DATN_BookWorms.services.PublishingCompanyService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/publishcompany")
public class PublishingCompaniesRestController {

	@Autowired
	PublishingCompanyService publishingCompanyService;
	
	@GetMapping
	public List<PublishingCompany> getAllPC(){
		return publishingCompanyService.findAll();
	}
}
