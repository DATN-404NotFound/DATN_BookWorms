package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.entities.Books;

@Service
public interface BookService {

	List<Books> findAll();

	Books findById(int id);

	List<Books> findByCategoryId(String cid);

	Books create(Books book);

	Books update(Books book);

	void delete(int id);
	
}
