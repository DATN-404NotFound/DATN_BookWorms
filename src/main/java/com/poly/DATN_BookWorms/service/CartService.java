package com.poly.DATN_BookWorms.service;

import java.util.List;
import java.util.Optional;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Shoponlines;
public interface CartService {
	
	public List<Cart> findAll();
	
	public List<Cart> findByUser(String userId);
	
	public List<Shoponlines> list_cart_shop(String userId);
	
	public Cart findById(int cartid);
	
	public Cart create(Cart cart);
	
	public void delete(Cart cart);
	
	public Cart update(Cart cart);

	
	
}
