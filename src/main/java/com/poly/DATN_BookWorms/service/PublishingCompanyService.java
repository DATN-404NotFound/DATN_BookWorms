package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Publishingcompanies;

@Service
public interface PublishingCompanyService {

	public List<Publishingcompanies> findAll();

}
