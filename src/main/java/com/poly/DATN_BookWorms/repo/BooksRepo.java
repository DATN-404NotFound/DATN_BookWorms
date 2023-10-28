package com.poly.DATN_BookWorms.repo;

import java.util.List;

import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Books;

public interface BooksRepo extends JpaRepository<Books, Integer>{

    Books findFirstByOrderByQuantitysoldDesc();

    //	@Query("SELECT DISTINCT b FROM Books p WHERE p.category.categoryid=?1")
//	List<Books> findByCategoryId(String cid);
//
//	@Query("SELECT DISTINCT o FROM Product o WHERE o.name LIKE ?1")
//	List<Books> findProductByName(String id);
@Query("SELECT new com.poly.DATN_BookWorms.response.BookResponse(bo.bookid, bo.bookname, ib.name, bo.price, SO.shopname) " +
        "FROM Books bo " +
        "INNER JOIN Imagebooks ib ON bo.bookid = ib.bookid " +
        "INNER JOIN Shoponlines SO ON bo.shopid = SO.shopid")
Page<BookResponse> findAllBook(Pageable pageable);
    @Query("SELECT new com.poly.DATN_BookWorms.response.BookResponse(bo.bookid, bo.bookname, ib.name, bo.price, SO.shopname) " +
            "FROM Books bo " +
            "INNER JOIN Imagebooks ib ON bo.bookid = ib.bookid " +
            "INNER JOIN Shoponlines SO ON bo.shopid = SO.shopid " +
            "INNER JOIN Typebooks tb ON bo.bookid = tb.bookid " +
            "INNER JOIN Categories C ON tb.categoryid = C.categoryid " +
            "WHERE C.name like %?1%")
    Page<BookResponse> findCategoryBook(String category,Pageable pageable);

    @Override
    List<Books> findAllById(Iterable<Integer> integers);

    @Query("SELECT b FROM Books b INNER JOIN b.listOfTypebooks tb WHERE tb.categories.categoryid = :categoryID")
    List<Books> findBooksByCategoryID(Integer categoryID);
}
