package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.repo.WrittingmastersRepo;
import com.poly.DATN_BookWorms.service.WriterMasterService;
import org.springframework.stereotype.Service;

@Service
public class WriterMasterServiceImp implements WriterMasterService{

	@Autowired
	WrittingmastersRepo writtingRepo;
	
	@Override
	public List<Writtingmasters> findAll() {
		// TODO Auto-generated method stub
		return writtingRepo.findAll();
	}

}
