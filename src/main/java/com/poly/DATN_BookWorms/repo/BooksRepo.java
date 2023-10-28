package com.poly.DATN_BookWorms.repo;



import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Books;

public interface BooksRepo extends JpaRepository<Books, Integer>{
	
//	@Query("SELECT DISTINCT b FROM Books p WHERE p.category.categoryid=?1")
//	List<Books> findByCategoryId(String cid);
//
//	@Query("SELECT DISTINCT o FROM Product o WHERE o.name LIKE ?1")
//	List<Books> findProductByName(String id);
}
