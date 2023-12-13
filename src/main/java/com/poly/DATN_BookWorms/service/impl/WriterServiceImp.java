package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import com.poly.DATN_BookWorms.repo.WrittingmastersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.repo.WritersRepo;
import com.poly.DATN_BookWorms.service.WriterService;

@Service
public class WriterServiceImp implements WriterService{
	@Autowired
	WrittingmastersRepo writtingmastersRepo;
	
	@Override
	public List<Writtingmasters> findAll() {
		// TODO Auto-generated method stub
		return writtingmastersRepo.findAll();
	}

	@Override
	public List<Writtingmasters> getWrittingWithSHop(Integer shopid) {
		// TODO Auto-generated method stub
		return null;
	}
}
