package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.service.BookService;

@Service
public class BookServiceImp implements BookService{
	BooksRepo booksRepo;
	@Autowired
	BooksRepo bookRepo;
	
	@Override
	public List<Books> findAll() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public Books findById(int id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id).get();
	}
	
//	@Override
//	public List<Books> findByCategoryId(String cid) {
//		return bookRepo.findByCategoryId(cid);
//	}

	@Override
	public Books create(Books book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	@Override
	public Books update(Books book) {
		// TODO Auto-generated method stub
		return bookRepo.save(book);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> findByCategoryId(String cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public BookServiceImp(BooksRepo booksRepo) {
		this.booksRepo = booksRepo;
	}

	@Override
	public Page<BookResponse> findAllBook(Pageable pageable) {
		return booksRepo.findAllBook(pageable);
	}
}
