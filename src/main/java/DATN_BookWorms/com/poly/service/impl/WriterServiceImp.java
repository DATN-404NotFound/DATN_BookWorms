package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entities.Writers;
import com.poly.repo.WritersRepo;
import com.poly.service.WriterService;

@Service
public class WriterServiceImp implements WriterService{
	@Autowired
	WritersRepo writerRepo;
	
	@Override
	public List<Writers> findAll() {
		// TODO Auto-generated method stub
		return writerRepo.findAll();
	}
}
