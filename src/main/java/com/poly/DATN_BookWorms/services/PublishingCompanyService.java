package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.PublishingCompany;
import org.springframework.stereotype.Service;

@Service
public interface PublishingCompanyService {
	 List<PublishingCompany> findAll();
}
