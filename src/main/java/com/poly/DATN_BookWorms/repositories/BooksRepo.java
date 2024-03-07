package com.poly.DATN_BookWorms.repositories;

import java.util.List;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.PublishingCompany;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import com.poly.DATN_BookWorms.responses.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepo extends JpaRepository<Book, Long> {
    @Query("Select b.publishingCompany from Book b where b.shopid = ?1")
    List<PublishingCompany> getPCWithShop(Integer shopid);


    Book findFirstByOrderByQuantitysoldDesc();

    @Query("SELECT new com.poly.DATN_BookWorms.responses.BookResponse(bo.bookid, bo.bookname, ib.name, bo.price, SO.shopname) " +
            "FROM Book bo " +
            "INNER JOIN ImageBook ib ON bo.bookid = ib.bookid " +
            "INNER JOIN ShopOnline SO ON bo.shopid = SO.shopid")
    Page<BookResponse> findAllBook(Pageable pageable);

    @Query("SELECT b FROM Book b INNER JOIN b.listOfTypeBook tb WHERE tb.category.categoryid = :categoryID")
    List<Book> findBooksByCategoryID(Integer categoryID);

    Page<Book> findByshopid(Integer shopid, Pageable pageable);

    @Query("SELECT b FROM Book b INNER JOIN b.listOfTypeBook tb WHERE tb.category.categoryid = :categoryID")
    Page<Book> findBooksByCategoryID(Integer categoryID, Pageable pageable);

    List<Book> findByShopid(Integer shopId);

    @Query("Select b.bookid from Book b where b.bookid in (Select t.bookid from TypeBook t where t.category.categoryid in ?1)")
    List<Integer> getListBookWithTypeBooks(List<Integer> listTypeBook);

    @Query("Select b.bookid from Book b where b.bookid in (Select w.bookid from Writer w where w.writtingmasterid in ?1)")
    List<Integer> getListBookWithWriter(List<Integer> listWriter);

    @Query("SELECT SUM(b.productviews) FROM Book b  WHERE b.shopOnline.shopid =?1 ")
    int getProductViews(Integer shopId);

    @Query("SELECT b FROM Book b GROUP BY b ORDER BY b.quantitysold DESC limit 5")
    List<Book> findTop5Seller();

    @Query("SELECT b FROM Book b GROUP BY b ORDER BY SUM(b.quantitysold) ASC limit 5")
    List<Book> findTop5Inventory();

    @Query("SELECT b FROM Book b WHERE b.shopid = ?1")
    List<Book> findBookByShopId(Integer shopId);

    @Query("Select b.shopOnline from Book b where b.bookid = ?1")
    ShopOnline s(long bookid);

    Optional<Book> findBybookid(Long bookId);

    @Query("Select b from Book b where b.shopOnline.shopid = ?1 order by b.productviews desc limit 10")
    List<Book> findByAccordingViewAndShopId(Integer shopid);
}

