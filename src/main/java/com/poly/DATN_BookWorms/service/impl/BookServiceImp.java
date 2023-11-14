package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Publishingcompanies;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.service.BookService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.DATN_BookWorms.response.BookResponse;

@Service
public class BookServiceImp implements BookService{

	@Autowired
	BooksRepo bookRepo;
	
	@Override
	public List<Books> findAll() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

	@Override
	public Books findById(Long id) {
		// TODO Auto-generated method stub
		return bookRepo.findById(id).get();
	}

	@Override
	public Page<Books> findAll(Pageable pageable) {
		return bookRepo.findAll(pageable);
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
	public void delete(Long id) {
		// TODO Auto-generated method stub
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> getBooksByCategoryID(Integer categoryID) {
		return bookRepo.findBooksByCategoryID(categoryID);
	}
	@Override
	public Page<BookResponse> findAllBook(Pageable pageable) {
		return bookRepo.findAllBook(pageable);
	}

	@Override
	public Books findTopBookByQuantitySold() {
		return bookRepo.findFirstByOrderByQuantitysoldDesc();
	}

	@Override
	public Page<Books> findByshopid(Integer shopid, Pageable pageable) {
		return bookRepo.findByshopid(shopid, pageable);
	}

	@Override
	public List<Books> findTop5LowestQuantityBooksByShopId(Integer shopId) {
		List<Books> booksWithSameShopId = bookRepo.findByShopid(shopId);
		booksWithSameShopId.sort((book1, book2) -> book1.getQuantity().compareTo(book2.getQuantity()));
		List<Books> top5LowestQuantityBooks = booksWithSameShopId.stream()
				.limit(5)
				.collect(Collectors.toList());
		return top5LowestQuantityBooks;
	}

	@Override
	public List<Publishingcompanies> getPCWithShop(Integer shopid) {
		// TODO Auto-generated method stub
		return bookRepo.getPCWithShop(shopid);
	}

	@Override
	public Page<Books> getBooksByCategoryID(Integer categories, Pageable pageable) {
		// TODO Auto-generated method stub
		return bookRepo.findBooksByCategoryID(categories, pageable);
	}

//	@Override
//	public Page<Books> findBooksNew(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Page<Books> findBooksNew(Pageable pageable) {
		// TODO Auto-generated method stub
		return bookRepo.findBooksNew(pageable);
	}

	@Override
	public List<Integer> getBookWithTypeBook(List<Integer> listtype) {
		// TODO Auto-generated method stub
		return bookRepo.getListBookWithTypeBooks(listtype);
	}

	@Override
	public List<Integer> getBookWithWriters(List<Integer> listwriter) {
		// TODO Auto-generated method stub
		return bookRepo.getListBookWithWriter(listwriter);
	}

	@Override
	public List<Integer> getBookWithEvaluate(List<Integer> listeva) {
		// TODO Auto-generated method stub
		return bookRepo.getListBookWithEvaluer(listeva);
	}
}
