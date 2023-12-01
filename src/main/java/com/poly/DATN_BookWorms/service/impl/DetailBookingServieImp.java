package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.repo.DetailbookingsRepo;
import com.poly.DATN_BookWorms.response.DetailBookingResponse;
import com.poly.DATN_BookWorms.service.DetailBookingService;

@Service
public class DetailBookingServieImp implements DetailBookingService{

	@Autowired
	BooksRepo booksRepo;
	
	@Override
	public List<Books> findTop5Seller() {
		// TODO Auto-generated method stub
		return booksRepo.findTop5Seller();
	}

	@Override
	public List<Books> findTop5Inventory() {
		// TODO Auto-generated method stub
		return booksRepo.findTop5Inventory();
	}

	
}
