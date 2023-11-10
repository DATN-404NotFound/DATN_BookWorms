package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writers;
import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.repo.WritersRepo;
import com.poly.DATN_BookWorms.service.WriterService;

@Service
public class WriterServiceImp implements WriterService{
	@Autowired
	WritersRepo writerRepo;
	
	@Override
	public List<Writers> findAll() {
		// TODO Auto-generated method stub
		return writerRepo.findAll();
	}

	@Override
	public List<Writtingmasters> getWrittingWithSHop(Integer shopid) {
		// TODO Auto-generated method stub
		return writerRepo.listWrittingByType(shopid);
	}
}
