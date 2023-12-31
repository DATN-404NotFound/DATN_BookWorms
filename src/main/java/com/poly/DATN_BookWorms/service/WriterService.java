package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Writers;
import com.poly.DATN_BookWorms.entities.Writtingmasters;

public interface WriterService {

	public List<Writtingmasters> findAll();
	 public List<Writtingmasters> getWrittingWithSHop(Integer shopid);

	Writers save(Writers writers);

	List<Writers> findByBookId(Integer bookId);
}
