package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Evaluates;
import com.poly.DATN_BookWorms.repo.EvaluatesRepo;
import com.poly.DATN_BookWorms.service.EvaluateService;

@Service
public class EvaluateServiceImp implements EvaluateService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	EvaluatesRepo evaluateRepo;
	
	@Override
	public Evaluates create(JsonNode evaluate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluates> findAll() {
		// TODO Auto-generated method stub
		return evaluateRepo.findAll();
	}



}
