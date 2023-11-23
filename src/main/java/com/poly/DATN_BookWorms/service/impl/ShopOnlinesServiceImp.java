package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;
import com.poly.DATN_BookWorms.service.ShopOnlinesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOnlinesServiceImp implements ShopOnlinesService {
    @Autowired
    ShoponlinesRepo shoponlinesRepo;
    @Override
    public Shoponlines findById(Integer id) {
        // Sử dụng repository để tìm một ShopOnline theo ID
        return shoponlinesRepo.findById(id).orElse(null);
    }
	@Override
	public List<Shoponlines> getAllListShop() {
		// TODO Auto-generated method stub
		return shoponlinesRepo.findAll();
	}
}
