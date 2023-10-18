package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poly.DATN_BookWorms.entities.Publishingcompanies;
import com.poly.DATN_BookWorms.repo.PublishingcompaniesRepo;
import com.poly.DATN_BookWorms.service.PublishingCompanyService;

public class PublishingCompanyServiceImp implements PublishingCompanyService{
	
	@Autowired
	PublishingcompaniesRepo pubRepo;
	
	@Override
	public List<Publishingcompanies> findAll() {
		// TODO Auto-generated method stub
		return pubRepo.findAll();
	}

}
