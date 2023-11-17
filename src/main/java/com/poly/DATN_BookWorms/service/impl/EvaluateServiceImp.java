package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Evaluates;
import com.poly.DATN_BookWorms.repo.EvaluatesRepo;
import com.poly.DATN_BookWorms.service.EvaluateService;

@Service
@Transactional
public class EvaluateServiceImp implements EvaluateService{

	@Autowired
	EvaluatesRepo evaluateRepo;
	@Autowired
	BooksRepo booksRepo;
	@Override
	public Evaluates create(JsonNode evaluate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Evaluates> findEvaluatesByShopId(Integer shopId) {

		List<Books> booksList = booksRepo.findByShopid(shopId);
		List<Detailbookings> detailbookingsList = booksList.stream()
				.flatMap(books -> books.getListOfDetailbookings().stream())
				.collect(Collectors.toList());


		List<Evaluates> evaluatesList = detailbookingsList.stream()
				.flatMap(detailbookings -> detailbookings.getListOfEvaluates().stream())
				.collect(Collectors.toList());

		return evaluatesList;
		}



	@Override
	public List<Evaluates> findAll() {
		// TODO Auto-generated method stub
		return evaluateRepo.findAll();
	}

}
