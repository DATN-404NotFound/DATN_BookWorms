package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.ImageBook;

public interface ImagebooksRepo extends JpaRepository<ImageBook, Integer>{

	@Query("Select i from ImageBook i where i.book.bookid = ?1")
	public List<ImageBook> findByBookid (Long bookid);

	List<ImageBook> findBybookid(Integer bookid);
}
