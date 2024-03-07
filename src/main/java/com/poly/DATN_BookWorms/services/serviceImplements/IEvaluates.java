package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.Evaluate;
import com.poly.DATN_BookWorms.repositories.EvaluatesRepo;
import com.poly.DATN_BookWorms.services.EvaluatesService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEvaluates implements EvaluatesService {
	
	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    EvaluatesRepo evaluatesRepo;
    @Override
    public Integer sumDbidByEvaluateId(Integer id) {
    	logger.info("find sumDbidByEvaluateId by id : {}",id);
        return evaluatesRepo.sumDbidByEvaluateId(id);
    }	
    
    @Override
	public List<Evaluate> getEvaByBookid(Long bookid) {
    	logger.info("find list Evalautes by bookid : {}",bookid);
		// TODO Auto-generated method stub
	return evaluatesRepo.getEvaByBookid(bookid);
	}
}
