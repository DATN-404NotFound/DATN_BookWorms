package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;

@Service
public interface BookService {

	List<Books> findAll();

	Books findById(int id);

	List<Books> findByCategoryId(String cid);

	Books create(Books book);

	Books update(Books book);

	void delete(int id);

	Page<BookResponse> findAllBook(Pageable pageable);



}
