package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;
import com.poly.DATN_BookWorms.service.ShopOnlineService;


@Service
public class ShopOnlineServiceImp implements ShopOnlineService{
	
	@Autowired
	ShoponlinesRepo shoponlinesRepo;
	 
	@Override
	public List<Shoponlines> findAll() {
		// TODO Auto-generated method stub
		return shoponlinesRepo.findAll();
	}

	@Override
	public Shoponlines findById(Integer id) {
		// TODO Auto-generated method stub
		return shoponlinesRepo.findById(id).get();
	}

	@Override
	public Shoponlines create(Books book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shoponlines update(Books book) {
		// TODO Auto-generated method stub
		return null;
	}

}
