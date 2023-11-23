package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Evaluates;
import com.poly.DATN_BookWorms.repo.EvaluatesRepo;
import com.poly.DATN_BookWorms.service.EvaluatesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluatesServiceImp implements EvaluatesService {
    @Autowired
    EvaluatesRepo evaluatesRepo;
    @Override
    public Integer sumDbidByEvaluateId(Integer id) {
        return evaluatesRepo.sumDbidByEvaluateId(id);
    }	
    
    @Override
	public List<Evaluates> getEvaByBookid(Long bookid) {
		// TODO Auto-generated method stub
	return evaluatesRepo.getEvaByBookid(bookid);
	}
}
