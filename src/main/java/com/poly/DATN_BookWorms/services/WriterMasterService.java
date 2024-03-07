package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.WritingMaster;
import org.springframework.stereotype.Service;

@Service
public interface WriterMasterService {

	public List<WritingMaster> findAll();

}
