package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Writers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poly.DATN_BookWorms.repo.WrittingmastersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Writtingmasters;
import com.poly.DATN_BookWorms.repo.WritersRepo;
import com.poly.DATN_BookWorms.service.WriterService;

@Service
public class WriterServiceImp implements WriterService{
	
	private static final Logger logger =  LogManager.getLogger();

	@Autowired
	WrittingmastersRepo writtingmastersRepo;

	@Autowired
	WritersRepo writersRepo;
	
	@Override
	public List<Writtingmasters> findAll() {
		// TODO Auto-generated method stub
		return writtingmastersRepo.findAll();
	}

	@Override
	public List<Writtingmasters> getWrittingWithSHop(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("get list writtingmaster with shopid : {}", shopid);
		return writersRepo.listWrittingByType(shopid);
	}
	@Override
	public Writers save(Writers writers) {
		return writersRepo.save(writers);
	}
	@Override
	public List<Writers> findByBookId(Integer bookId) {
		return writersRepo.findBybookid(bookId);
	}
}
