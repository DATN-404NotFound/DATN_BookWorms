package com.poly.DATN_BookWorms.repo;

import java.util.List;

<<<<<<< HEAD
import com.poly.DATN_BookWorms.response.BookResponse;
import org.springframework.data.domain.Example;
=======
import java.util.List;

>>>>>>> zendyy/back_end
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Publishingcompanies;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.response.BookResponse;

<<<<<<< HEAD
import java.util.List;

public interface BooksRepo extends JpaRepository<Books, Long> {
@Query("Select b.publishingcompanies from Books b where b.shopid = ?1")
public List<Publishingcompanies> getPCWithShop(Integer shopid);


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
            "INNER JOIN Categories C ON tb.categories.categoryid = C.categoryid " +
            "WHERE C.name like %?1%")
    Page<BookResponse> findCategoryBook(String category, Pageable pageable);



    @Query("SELECT b FROM Books b INNER JOIN b.listOfTypebooks tb WHERE tb.categories.categoryid = :categoryID")
    List<Books> findBooksByCategoryID(Integer categoryID);

    Page<Books> findByshopid(Integer shopid, Pageable pageable);
    
    @Query("SELECT b FROM Books b INNER JOIN b.listOfTypebooks tb WHERE tb.categories.categoryid = :categoryID")
    Page<Books> findBooksByCategoryID(Integer categoryID, Pageable pageable);
    List<Books> findByShopid(Integer shopId);
    
    @Query("SELECT b FROM Books b  ORDER BY b.publishingyear DESC ")
    Page<Books> findBooksNew(Pageable pageable);
    
    @Query("Select b.bookid from Books b where b.bookid in (Select t.bookid from Typebooks t where t.categories.categoryid in ?1)")
    List<Integer> getListBookWithTypeBooks(List<Integer> listTypeBook);
    
    @Query("Select b.bookid from Books b where b.bookid in (Select w.bookid from Writers w where w.writtingmasterid in ?1)")
    List<Integer> getListBookWithWriter(List<Integer> listWriter);
    
    
    @Query("Select b.bookid from Books b where b.bookid in (Select d.bookid from Detailbookings d where d.dbid in (Select e.dbid from Evaluates e where e.rating in ?1))")
    List<Integer> getListBookWithEvaluer(List<Integer> ratinglist);


    @Query("SELECT SUM(b.productviews) FROM Books b  WHERE b.shoponlines.shopid =?1 ")
    int getProductViews(Integer shopId);
=======
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
    
    @Query("SELECT b FROM Books b GROUP BY b ORDER BY b.quantitysold DESC limit 5")
	List<Books> findTop5Seller();
	
	@Query("SELECT b FROM Books b GROUP BY b ORDER BY SUM(b.quantitysold) ASC limit 5")
	List<Books> findTop5Inventory();
>>>>>>> zendyy/back_end
}

