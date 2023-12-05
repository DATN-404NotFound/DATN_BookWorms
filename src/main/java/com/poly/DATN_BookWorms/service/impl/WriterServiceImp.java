package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writers;
import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.repo.WritersRepo;
import com.poly.DATN_BookWorms.service.WriterService;

@Service
public class WriterServiceImp implements WriterService{
	
	private static final Logger logger = LogManager.getLogger();
	
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
		logger.info("get list writtingmaster with shopid : {}", shopid);
		return writerRepo.listWrittingByType(shopid);
	}
}
