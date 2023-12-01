package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.PaymentShopRepo;
import com.poly.DATN_BookWorms.service.*;

@Service
public class PaymentShopServiceImp implements PaymentShopService {

	@Autowired
	PaymentShopRepo paymentShopRepo;
	
	@Override
	public List<PaymentShop> findAll() {
		// TODO Auto-generated method stub
		return paymentShopRepo.findAll();
	}

	@Override
	public Shoponlines findShopId(int id) {
		// TODO Auto-generated method stub
		return paymentShopRepo.findShopId(id);
	}

	
}
