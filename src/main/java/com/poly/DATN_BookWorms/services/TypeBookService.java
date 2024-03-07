package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.Category;
import com.poly.DATN_BookWorms.entities.TypeBook;
import org.springframework.stereotype.Service;

@Service
public interface TypeBookService {

    List<Category> getCategoriesWithShop(Integer shopid);

    TypeBook create(Book book, Category category);

    TypeBook save(TypeBook typeBook);

    List<Category> findCateByBookId(int bookid);

    TypeBook update(TypeBook typeBook);

    List<TypeBook> findByBookId(Integer bookId);
}
