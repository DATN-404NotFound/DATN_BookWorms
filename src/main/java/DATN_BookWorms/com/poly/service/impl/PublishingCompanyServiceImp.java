package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poly.entities.Publishingcompanies;
import com.poly.repo.PublishingcompaniesRepo;
import com.poly.service.PublishingCompanyService;

public class PublishingCompanyServiceImp implements PublishingCompanyService{
	
	@Autowired
	PublishingcompaniesRepo pubRepo;
	
	@Override
	public List<Publishingcompanies> findAll() {
		// TODO Auto-generated method stub
		return pubRepo.findAll();
	}

}
