package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.poly.DATN_BookWorms.entities.TypeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.poly.DATN_BookWorms.repositories.TypebooksRepo;
import com.poly.DATN_BookWorms.services.TypeBookService;


@Service
public class ITypeBook implements TypeBookService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    TypebooksRepo typebooksRepo;

    @Override
    public List<Category> getCategoriesWithShop(Integer shopid) {
        logger.info("get list categories with shopid :{}", shopid);
        // TODO Auto-generated method stub
        return typebooksRepo.listCategoriesByType(shopid);
    }

    @Override
    public TypeBook create(Book book, Category category) {
        TypeBook typeBook = new TypeBook();
        typeBook.setBookid(book.getBookid().intValue());
        typeBook.setCategory(category);
        typeBook.setBook(book);
        return typebooksRepo.save(typeBook);
    }

    @Override
    public TypeBook save(TypeBook typeBook) {
        return typebooksRepo.save(typeBook);
    }

    @Override
    public List<Category> findCateByBookId(int bookid) {
        return typebooksRepo.findCateByBookId(bookid);
    }

    @Override
    public TypeBook update(TypeBook typeBook) {
        TypeBook existingTypeBook = typebooksRepo.findById(typeBook.getTypebookid())
                .orElseThrow(() -> new RuntimeException("Typebooks not found with typebookid: " + typeBook.getTypebookid()));
        return typebooksRepo.save(existingTypeBook);
    }

    @Override
    public List<TypeBook> findByBookId(Integer bookId) {
        return typebooksRepo.findBybookid(bookId);
    }
}