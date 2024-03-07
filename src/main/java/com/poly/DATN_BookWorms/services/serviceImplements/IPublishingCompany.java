package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.PublishingCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repositories.PublishingcompaniesRepo;
import com.poly.DATN_BookWorms.services.PublishingCompanyService;

@Service
public class IPublishingCompany implements PublishingCompanyService{
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	PublishingcompaniesRepo pubRepo;
	
	@Override
	public List<PublishingCompany> findAll() {
		// TODO Auto-generated method stub
		return pubRepo.findAll();
	}


}
