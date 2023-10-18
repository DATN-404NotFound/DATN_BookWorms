package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poly.entities.Writtingmasters;
import com.poly.repo.WrittingmastersRepo;
import com.poly.service.WriterMasterService;

public class WriterMasterServiceImp implements WriterMasterService{

	@Autowired
	WrittingmastersRepo writtingRepo;
	
	@Override
	public List<Writtingmasters> findAll() {
		// TODO Auto-generated method stub
		return writtingRepo.findAll();
	}

}
