package com.poly.DATN_BookWorms.service;

import java.util.List;

import com.poly.DATN_BookWorms.repo.BooksRepo;
import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.response.BookResponse;


public interface BookService {

	List<Books> findAll();

	Books findById(Long id);
	Page<Books> findAll(Pageable pageable);

	Books create(Books book);

	Books update(Books book);

	void delete(Long id);
	Page<BookResponse> findAllBook(Pageable pageable);

	List<Books> getBooksByCategoryID(Integer categoryID);
	Books findTopBookByQuantitySold();

	Page<Books> findByshopid(Integer shopid, Pageable pageable);
	List<Books> findTop5LowestQuantityBooksByShopId(Integer shopId);
}
