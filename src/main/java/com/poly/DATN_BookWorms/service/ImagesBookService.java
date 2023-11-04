package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Imagebooks;

public interface ImagesBookService {
	
	public List<Imagebooks> findByBookId(Long bookid);
	

}
