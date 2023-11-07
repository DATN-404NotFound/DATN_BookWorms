package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Imagebooks;
import com.poly.DATN_BookWorms.repo.ImagebooksRepo;
import com.poly.DATN_BookWorms.service.ImagesBookService;

@Service
public class ImagesBookServiceImp implements ImagesBookService {

	@Autowired
	ImagebooksRepo imagebooksRepo;
	
	@Override
	public List<Imagebooks> findByBookId(Long bookid) {
		// TODO Auto-generated method stub
		System.out.println("In ra "+ imagebooksRepo.findByBookid(bookid));
		return imagebooksRepo.findByBookid(bookid);
	}

}
