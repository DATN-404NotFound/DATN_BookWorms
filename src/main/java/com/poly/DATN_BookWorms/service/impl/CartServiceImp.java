package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.CartRepo;
import com.poly.DATN_BookWorms.service.CartService;

@Service
public class CartServiceImp implements CartService {
	
	@Autowired
	CartRepo cartRepo;
	
	@Override
	public List<Cart> findAll() {
		// TODO Auto-generated method stub
		return cartRepo.findAll();
	}

	@Override
	public Cart findById(int cartid) {
		// TODO Auto-generated method stub
		return cartRepo.findById(cartid).get();
	}

	@Override
	public Cart create(Cart cart) {
//		 
		// TODO Auto-generated method stub
		return cartRepo.save(cart);
	}

	@Override
	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		cartRepo.delete(cart);
	}

	@Override
	public Cart update(Cart cart) {
		// TODO Auto-generated method stub
		return cartRepo.save(cart);
	}

	@Override
	public List<Cart> findByUser(String userid) {
		// TODO Auto-generated method stub
		return cartRepo.findCartByUser(userid);
	}

	@Override
	public List<Shoponlines> list_cart_shop(String userId) {
		// TODO Auto-generated method stub
		return cartRepo.list_cart_shopId(userId);
	}
	
	
	
}
