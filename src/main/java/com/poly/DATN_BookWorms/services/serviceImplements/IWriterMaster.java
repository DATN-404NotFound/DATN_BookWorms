package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.WritingMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repositories.WrittingmastersRepo;
import com.poly.DATN_BookWorms.services.WriterMasterService;

@Service
public class IWriterMaster implements WriterMasterService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	WrittingmastersRepo writtingRepo;
	
	@Override
	public List<WritingMaster> findAll() {
		// TODO Auto-generated method stub
		return writtingRepo.findAll();
	}

}
