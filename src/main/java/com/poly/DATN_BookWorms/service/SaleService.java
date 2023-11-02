package com.poly.DATN_BookWorms.service;

	import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Sales;


public interface SaleService {

	List<Sales> findAll();

	Sales findById(String id);

	Sales create(Sales sale);

	Sales update(Sales sale);

	void delete(String id);

	List<Sales> saleOfShopIntendFor(String intendFor);
	
}
