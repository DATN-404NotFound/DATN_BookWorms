package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.repositories.ImagebooksRepo;
import com.poly.DATN_BookWorms.repositories.TypebooksRepo;
import com.poly.DATN_BookWorms.responses.BookResponse;
import com.poly.DATN_BookWorms.utils.SessionService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.repositories.BooksRepo;
import com.poly.DATN_BookWorms.services.BookService;

@Service
public class IBook implements BookService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    BooksRepo bookRepo;
    @Autowired
    ImagebooksRepo imagebooksRepo;
    @Autowired
    TypebooksRepo typebooksRepo;
    @Autowired
    SessionService sessionService;

    @Override
    public List<Book> findAll() {
        // TODO Auto-generated method stub
        logger.info("get all books : {}", bookRepo.findAll());
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long id) {
        // TODO Auto-generated method stub
        logger.info("get book by id have id : {} abd return  : {}", bookRepo.findById(id).get());
        return bookRepo.findById(id).get();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public Book creates(Book books) {
        Account user = sessionService.get("user");
        books.setBookname(books.getBookname());
        books.setLanguage(books.getLanguage());
        books.setSize(books.getSize());
        books.setWeight(books.getWeight());
        books.setTotalpage(books.getTotalpage());
        books.setPublishingyear(books.getPublishingyear());
        books.setPrice(books.getPrice());
        books.setQuantity(books.getQuantity());
        books.setStatues("Còn hàng");
        books.setPublishingcompanyid(books.getPublishingcompanyid());
        books.setIsactive(books.getIsactive());
        books.setQuantitysold(0);
        books.setShopid(user.getListOfShopOnline().get(0).getShopid());
        books.setProductviews(0);
        books.setQuantitysold(books.getQuantity());
        books.setIsdelete(false);
        return bookRepo.save(books);
    }

    @Override
    public Book update(Book book) {
        if (book.getBookid() == null) {
            throw new IllegalArgumentException("Books ID cannot be null for update operation");
        }
        Account user = sessionService.get("user");
        Book existingBooks = bookRepo.findById(book.getBookid())
                .orElseThrow(() -> new EntityNotFoundException("Books not found with ID: " + book.getBookid()));
        book.setBookname(book.getBookname());
        book.setLanguage(book.getLanguage());
        book.setSize(book.getSize());
        book.setWeight(book.getWeight());
        book.setTotalpage(book.getTotalpage());
        book.setPublishingyear(book.getPublishingyear());
        book.setPrice(book.getPrice());
        book.setQuantity(book.getQuantity());
        book.setStatues("Còn hàng");
        book.setPublishingcompanyid(book.getPublishingcompanyid());
        book.setIsactive(book.getIsactive());
        book.setQuantitysold(0);
        book.setShopid(user.getListOfShopOnline().get(0).getShopid());
        book.setProductviews(0);
        book.setQuantitysold(book.getQuantity());
        book.setIsdelete(false);
        return bookRepo.save(existingBooks);
    }


    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        logger.info("delete book with id : {}", id);
        bookRepo.deleteById(id);
    }

    @Override
    public List<Book> getBooksByCategoryID(Integer categoryID) {
        logger.info("getBooksByCategoryID with categoryID : {}", categoryID);
        return bookRepo.findBooksByCategoryID(categoryID);
    }

    @Override
    public Page<BookResponse> findAllBook(Pageable pageable) {
        logger.info("findAllBook  with pageable : {}", pageable);
        return bookRepo.findAllBook(pageable);
    }

    @Override
    public Book findTopBookByQuantitySold() {
        return bookRepo.findFirstByOrderByQuantitysoldDesc();
    }

    @Override
    public Page<Book> findByshopid(Integer shopid, Pageable pageable) {
        logger.info("findBookByshopid with shopid : {} and pageable : {}", shopid, pageable);
        return bookRepo.findByshopid(shopid, pageable);
    }

    public List<Book> findByshopidv2(Integer shopid) {
        List<Book> allBooks = bookRepo.findByShopid(shopid);
        List<Book> activeBooks = allBooks.stream()
                .filter(book -> Boolean.FALSE.equals(book.getIsdelete()))
                .collect(Collectors.toList());
        return activeBooks;
    }
    @Override
    public List<PublishingCompany> getPCWithShop(Integer shopid) {
        // TODO Auto-generated method stub
        logger.info("getPCWithShop with shopid : {}", shopid);
        return bookRepo.getPCWithShop(shopid);
    }

    @Override
    public Page<Book> getBooksByCategoryID(Integer categories, Pageable pageable) {
        // TODO Auto-generated method stub
        logger.info("getBooksByCategoryID with shopid : {} and pageable : {}", categories, pageable);
        return bookRepo.findBooksByCategoryID(categories, pageable);
    }

    @Override
    public void updateIsActive(Long bookId, boolean newIsActive) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setIsactive(newIsActive);
            bookRepo.save(book);
        }
    }

    @Override
    public List<Integer> getBookWithTypeBook(List<Integer> listtype) {
        // TODO Auto-generated method stub
        logger.info("getBookWithTypeBook with listtype : {}", listtype);
        return bookRepo.getListBookWithTypeBooks(listtype);
    }

    @Override
    public List<Integer> getBookWithWriters(List<Integer> listwriter) {
        // TODO Auto-generated method stub
        logger.info("getBookWithWriters with listwriter : {}", listwriter);
        return bookRepo.getListBookWithWriter(listwriter);
    }

    @Override
    public List<Book> findByShopList(Integer shopid) {
        // TODO Auto-generated method stub
        logger.info("findByShopList with shopid : {}", shopid);
        return bookRepo.findByShopid(shopid);
    }

    @Override
    public void deleteBook(Long bookId, boolean newIsActive) {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setIsdelete(newIsActive);
            bookRepo.save(book);
        }
    }

    @Override
    public Book save(Book book) {
        bookRepo.save(book);
        return book;
    }

    @Override
    public Optional<Book> findByBookId(Long bookId) {
        return bookRepo.findBybookid(bookId);
    }

}
