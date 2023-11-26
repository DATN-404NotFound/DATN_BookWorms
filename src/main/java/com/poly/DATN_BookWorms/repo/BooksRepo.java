package com.poly.DATN_BookWorms.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.response.BookResponse;

public interface BooksRepo extends JpaRepository<Books, Integer>{
	

	@Query("SELECT new com.poly.DATN_BookWorms.response.BookResponse(bo.bookid, bo.bookname, ib.name, bo.price, SO.shopname) " +
	        "FROM Books bo " +
	        "INNER JOIN Imagebooks ib ON bo.bookid = ib.bookid " +
	        "INNER JOIN Shoponlines SO ON bo.shopid = SO.shopid")
	Page<BookResponse> findAllBook(Pageable pageable);
	
    @Query("SELECT b2 FROM Books b1 "
    		+ "JOIN Typebooks tb1 ON b1.bookid = tb1.bookid "
    		+ "JOIN Typebooks tb2 ON tb1.categories.categoryid = tb2.categories.categoryid "
    		+ "JOIN Books b2 ON tb2.bookid = b2.bookid "
    		+ "WHERE b1.bookid = ?1")
    List<Books> findRelatedBooks(@Param("bookId") Integer bookId);
    
    @Query("SELECT b FROM Books b GROUP BY b ORDER BY SUM(b.quantitysold) DESC limit 5")
	List<Books> findTop5Seller();
	
	@Query("SELECT b FROM Books b GROUP BY b ORDER BY SUM(b.quantitysold) ASC limit 5")
	List<Books> findTop5Inventory();
}
