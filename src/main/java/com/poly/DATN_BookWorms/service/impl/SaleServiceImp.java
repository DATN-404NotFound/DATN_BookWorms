package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Sales;
import com.poly.DATN_BookWorms.repo.SalesRepo;
import com.poly.DATN_BookWorms.service.SaleService;

@Service
public class SaleServiceImp implements SaleService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	SalesRepo saleRepo;
	
	@Override
	public List<Sales> findAll() {
		// TODO Auto-generated method stub
		return saleRepo.findAll();
	}

	@Override
	public Sales findById(String id) {
		logger.info("find sale with saleid : {}",id);
		return saleRepo.findById(id).get();
	}

	@Override
	public Sales create(Sales sale) {
		logger.info("create sale with sale : {}",sale);
		return saleRepo.save(sale);
	}

	@Override
	public Sales update(Sales sale) {
		logger.info("update sale with sale : {}",sale);
		return saleRepo.save(sale);
	}

	@Override
	public void delete(String id) {
		logger.info("delete sales with id : {}",id);
		saleRepo.deleteById(id);;
	}

	@Override
	public List<Sales> saleOfShopIntendFor(String intendFor){ 
		logger.info("get list sales with intendFor : {}",intendFor);
		return saleRepo.sales_of_shop_for_intendfor(intendFor);
	}

}
