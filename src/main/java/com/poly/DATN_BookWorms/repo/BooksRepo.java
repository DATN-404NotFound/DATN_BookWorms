package com.poly.DATN_BookWorms.repo;

import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Books;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepo extends JpaRepository<Books, Integer> {

   @Query("SELECT bo.bookname, ib.name, bo.price, so.shopname FROM Books bo " +
           "INNER JOIN bo.listOfImagebooks ib " +
           "INNER JOIN bo.shoponlines so")
   Page<BookResponse> findAllBook(Pageable pageable);



}
