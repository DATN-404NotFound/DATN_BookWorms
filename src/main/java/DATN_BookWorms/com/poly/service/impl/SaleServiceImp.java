package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entities.Sales;
import com.poly.repo.SalesRepo;
import com.poly.service.SaleService;

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
		// TODO Auto-generated method stub
		return saleRepo.findById(id).get();
	}

	@Override
	public Sales create(Sales sale) {
		// TODO Auto-generated method stub
		return saleRepo.save(sale);
	}

	@Override
	public Sales update(Sales sale) {
		// TODO Auto-generated method stub
		return saleRepo.save(sale);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		saleRepo.deleteById(id);;
	}

}
