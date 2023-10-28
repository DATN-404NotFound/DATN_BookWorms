package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.repo.SalesRepo;
import com.poly.DATN_BookWorms.service.SaleService;

@Service
public class SaleServiceImp implements SaleService{

	@Autowired
	SalesRepo saleRepo;
	
	@Override
	public List<Sales> findAll() {
		// TODO Auto-generated method stub
		return saleRepo.findAll();
	}

	@Override
	public Sales findById(String id) {
		return saleRepo.findById(id).get();
	}

	@Override
	public Sales create(Sales sale) {
		return saleRepo.save(sale);
	}

	@Override
	public Sales update(Sales sale) {
		return saleRepo.save(sale);
	}

	@Override
	public void delete(String id) {

		saleRepo.deleteById(id);;
	}

	@Override
	public List<Sales> saleOfShopIntendFor(String intendFor){ 
		return saleRepo.sales_of_shop_for_intendfor(intendFor);
	}

}
