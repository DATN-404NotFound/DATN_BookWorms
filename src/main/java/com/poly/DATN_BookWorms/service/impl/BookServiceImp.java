package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.response.BookResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	BooksRepo bookRepo;
	
	@Override
	public List<Books> findAll() {
		// TODO Auto-generated method stub
		logger.info("get all books : {}", bookRepo.findAll());
		return bookRepo.findAll();
	}

	@Override
	public Books findById(Long id) {
		// TODO Auto-generated method stub
		logger.info("get book by id have id : {} abd return  : {}",bookRepo.findById(id).get());
		return bookRepo.findById(id).get();
	}

	@Override
	public Page<Books> findAll(Pageable pageable) {
		logger.info("get all books  return pageable : {}", bookRepo.findAll(pageable));
		return bookRepo.findAll(pageable);
	}


//	@Override
//	public List<Books> findByCategoryId(String cid) {
//		return bookRepo.findByCategoryId(cid);
//	}

	@Override
	public Books create(Books book) {
		// TODO Auto-generated method stub
		logger.info("book to create- book : {}", book);
		return bookRepo.save(book);
	}

	@Override
	public Books update(Books book) {
		// TODO Auto-generated method stub
		logger.info("update book with book : {}", book);
		return bookRepo.save(book);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		logger.info("delete book with id : {}", id);
		bookRepo.deleteById(id);
	}

	@Override
	public List<Books> getBooksByCategoryID(Integer categoryID) {
		logger.info("getBooksByCategoryID with categoryID : {}", categoryID);
		return bookRepo.findBooksByCategoryID(categoryID);
	}
	@Override
	public Page<BookResponse> findAllBook(Pageable pageable) {
		logger.info("findAllBook  with pageable : {}", pageable);
		return bookRepo.findAllBook(pageable);
	}

	@Override
	public Books findTopBookByQuantitySold() {
		return bookRepo.findFirstByOrderByQuantitysoldDesc();
	}

	@Override
	public Page<Books> findByshopid(Integer shopid, Pageable pageable) {
		logger.info("findBookByshopid with shopid : {} and pageable : {}", shopid,pageable);
		return bookRepo.findByshopid(shopid, pageable);
	}

	@Override
	public List<Books> findTop5LowestQuantityBooksByShopId(Integer shopId) {
		logger.info("findTop5LowestQuantityBooksByShopId with shopid : {} ", shopId);
		try {
			List<Books> booksWithSameShopId = bookRepo.findByShopid(shopId);
			booksWithSameShopId.sort((book1, book2) -> book1.getQuantity().compareTo(book2.getQuantity()));
			List<Books> top5LowestQuantityBooks = booksWithSameShopId.stream()
					.limit(5)
					.collect(Collectors.toList());
			logger.info("findTop5LowestQuantityBooksByShopId with return  booksWithSameShopId.size: {} ", booksWithSameShopId.size());
			logger.info("findTop5LowestQuantityBooksByShopId with return  top5LowestQuantityBooks.size: {} ", top5LowestQuantityBooks.size());
			return top5LowestQuantityBooks;
		} catch (Exception e) {
			logger.error("findTop5LowestQuantityBooksByShopId find failed with shopid : {}", shopId);
			return null;
			// TODO: handle exception
		}
	}

	@Override
	public List<Publishingcompanies> getPCWithShop(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("getPCWithShop with shopid : {}", shopid);
		return bookRepo.getPCWithShop(shopid);
	}

	@Override
	public Page<Books> getBooksByCategoryID(Integer categories, Pageable pageable) {
		// TODO Auto-generated method stub
		logger.info("getBooksByCategoryID with shopid : {} and pageable : {}", categories,pageable);
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
		logger.info("findBooksNew with pageable : {}",pageable);
		return bookRepo.findBooksNew(pageable);
	}

	@Override
	public List<Integer> getBookWithTypeBook(List<Integer> listtype) {
		// TODO Auto-generated method stub
		logger.info("getBookWithTypeBook with listtype : {}",listtype);
		return bookRepo.getListBookWithTypeBooks(listtype);
	}

	@Override
	public List<Integer> getBookWithWriters(List<Integer> listwriter) {
		// TODO Auto-generated method stub
		logger.info("getBookWithWriters with listwriter : {}",listwriter);
		return bookRepo.getListBookWithWriter(listwriter);
	}

	@Override
	public List<Integer> getBookWithEvaluate(List<Integer> listeva) {
		// TODO Auto-generated method stub
		logger.info("getBookWithEvaluate with listeva : {}",listeva);
		return bookRepo.getListBookWithEvaluer(listeva);
	}

	@Override
	public List<Books> findByShopList(Integer shopid) {
		// TODO Auto-generated method stub
		logger.info("findByShopList with shopid : {}",shopid);
		return bookRepo.findByShopid(shopid);
	}
}
