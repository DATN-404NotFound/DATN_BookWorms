package com.poly.DATN_BookWorms.services;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
	
	public List<Cart> findAll();
	
	public List<Cart> findByUser();
	
	public List<ShopOnline> list_cart_shop();
	
	public Cart findById(Long cartid);
	
	public Cart create(Cart cart);
	
	public void delete(Long cartid);
	
	public Cart update(Cart cart);

}
