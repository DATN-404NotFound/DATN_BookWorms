package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Imagebooks;
import com.poly.DATN_BookWorms.repo.ImagebooksRepo;
import com.poly.DATN_BookWorms.service.ImagesBookService;

@Service
public class ImagesBookServiceImp implements ImagesBookService {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	ImagebooksRepo imagebooksRepo;
	
	@Override
	public List<Imagebooks> findByBookId(Long bookid) {
		// TODO Auto-generated method stub
		System.out.println("In ra "+ imagebooksRepo.findByBookid(bookid));
		logger.info("find list imagesBook with bookid : {}", bookid);
		return imagebooksRepo.findByBookid(bookid);
	}

}
