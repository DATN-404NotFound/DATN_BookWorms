package com.poly.DATN_BookWorms.services;

import java.util.List;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.PublishingCompany;
import com.poly.DATN_BookWorms.responses.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Page<Book> findAll(Pageable pageable);

    List<PublishingCompany> getPCWithShop(Integer shopid);

    Book creates(Book books);

    Book update(Book book);

    void delete(Long id);

    Page<BookResponse> findAllBook(Pageable pageable);

    List<Book> getBooksByCategoryID(Integer categoryID);

    Book findTopBookByQuantitySold();

    Page<Book> getBooksByCategoryID(Integer categories, Pageable pageable);

    Page<Book> findByshopid(Integer shopid, Pageable pageable);

    List<Book> findByshopidv2(Integer shopid);

    void updateIsActive(Long bookId, boolean newIsActive);

    List<Book> findByShopList(Integer shopid);

    List<Integer> getBookWithTypeBook(List<Integer> listtype);

    List<Integer> getBookWithWriters(List<Integer> listwriter);

    void deleteBook(Long bookId, boolean newIsActive);

    Book save(Book book);

    Optional<Book> findByBookId(Long bookId);
}
