package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entities.Evaluates;
import com.poly.entities.Typebooks;
import com.poly.repo.EvaluatesRepo;
import com.poly.service.EvaluateService;

public class EvaluateServiceImp implements EvaluateService{

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
