package com.poly.DATN_BookWorms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Shoponlines;


public interface CartRepo extends JpaRepository<Cart,Integer> {

	@Query("Select a from Cart c where c.account.userid like ?1")
	List<Cart> findCartByUser(String userid);
	
	@Query("Select b.shopOnline from Books b where b.bookId in (Select c.bookid from cart c where c.userid like ?1 )")
	List<Shoponlines> list_cart_shopId(String userid);
	
}
