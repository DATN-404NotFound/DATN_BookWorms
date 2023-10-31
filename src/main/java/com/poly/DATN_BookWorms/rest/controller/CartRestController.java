package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Books;
import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.service.CartService;

@CrossOrigin("*")
@RestController

public class CartRestController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/rest/cart")
	public List<Cart> selectAllCart(){ 
		return cartService.findAll();
	}
	@GetMapping("/rest/cart/user")
	public List<Cart> selectUserCart(){ 
		return cartService.findByUser();
	}
	
//	@GetMapping("")
//	public List<Cart> selectCartOfUser(@RequestParam("userid") Optional<String> userId){ 
//		return cartService.findByUser(userId);
//	}
	
	@GetMapping("/rest/cart/{cartid}")
	public Cart SelectById(@PathVariable("cartid") Integer cartid){ 
		System.out.println("jkkkk");
		return cartService.findById(cartid);
	}
	
	@PostMapping("/rest/cart")
	public void post(@RequestBody Cart auth) { 
		System.out.println("kkkkkkkkkn");
	}
	
	@PutMapping("/rest/cart")
	public Cart updateCart(@RequestBody Cart cart){ 
		System.out.println("update");
		return cartService.update(cart);
	}
	
	@DeleteMapping("/rest/cart/{cartid}")
	public void delete(@PathVariable("cartid") int cartid) { 
		System.out.println("cartid "+ cartid);
		cartService.delete(cartid);
	}
}
