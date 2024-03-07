package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Writer;
import com.poly.DATN_BookWorms.entities.WritingMaster;
import org.springframework.stereotype.Service;

@Service
public interface WriterService {

	public List<WritingMaster> findAll();
	 public List<WritingMaster> getWrittingWithSHop(Integer shopid);

	Writer save(Writer writer);

	List<Writer> findByBookId(Integer bookId);
}
