package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poly.DATN_BookWorms.entities.Cart;


public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("Select c from Cart c where c.account.userid like ?1")
    List<Cart> findCartByUser(String userid);

    @Query("Select b.shopOnline from Book b where b.bookid in (Select c.book.bookid from Cart c where c.account.userid like ?1 )")
    List<ShopOnline> list_cart_shopId(String userid);

}
