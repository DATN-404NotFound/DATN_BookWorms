package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entities.Books;

public interface BooksRepo extends JpaRepository<Books, Integer>{
	
	@Query("SELECT DISTINCT b FROM Books p WHERE p.category.categoryid=?1")
	List<Books> findByCategoryId(String cid);

	@Query("SELECT DISTINCT o FROM Product o WHERE o.name LIKE ?1")
	List<Books> findProductByName(String id);
}
