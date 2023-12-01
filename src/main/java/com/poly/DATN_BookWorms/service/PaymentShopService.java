package com.poly.DATN_BookWorms.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;

@Service
public interface PaymentShopService {
	
	List<PaymentShop> findAll();

	Shoponlines findShopId(int id);
}
