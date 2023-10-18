package com.poly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entities.Sales;

@Service
public interface SaleService {

	List<Sales> findAll();

	Sales findById(String id);

	Sales create(Sales sale);

	Sales update(Sales sale);

	void delete(String id);
	
}
