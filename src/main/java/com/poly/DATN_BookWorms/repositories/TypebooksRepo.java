package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.TypeBook;

public interface TypebooksRepo extends JpaRepository<TypeBook, Integer> {

    @Query("Select t.category from TypeBook t where t.bookid  = ?1")
    List<Category> findCateByBookId(int bookid);

    @Query("Select t.category from TypeBook t where t.bookid in (Select b.bookid from Book b where b.shopid = ?1)")
    List<Category> listCategoriesByType(Integer shopid);

    List<TypeBook> findBybookid(Integer bookId);
}
