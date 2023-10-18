package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entities.Evaluates;
import com.poly.entities.Typebooks;

@Service
public interface EvaluateService {

	Evaluates create(JsonNode evaluate);

	public List<Evaluates> findAll();

}
