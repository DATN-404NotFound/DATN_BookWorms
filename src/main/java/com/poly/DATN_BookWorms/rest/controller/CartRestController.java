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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Cart;
import com.poly.DATN_BookWorms.service.CartService;

@RestController
@CrossOrigin("")
@RequestMapping("/rest/cart")
public class CartRestController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping("")
	public List<Cart> selectAllCart(){ 
		return cartService.findAll();
	}
	
//	@GetMapping("")
//	public List<Cart> selectCartOfUser(@RequestParam("userid") Optional<String> userId){ 
//		return cartService.findByUser(userId);
//	}
	
	@GetMapping("/{carid}")
	public Cart SelectById(@PathVariable("cartid") Integer cartid){ 
		return cartService.findById(cartid);
	}
	
	@PostMapping("/addCart")
	public Cart addCart(@RequestBody Cart cart){ 
		return cartService.create(cart);
	}
	
	
	@PutMapping("/updateCart")
	public Cart updateCart(@RequestBody Cart cart){ 
		return cartService.update(cart);
	}
	
	@DeleteMapping("/deleteCart")
	public void deteleCart(@RequestBody Cart cart){ 
		cartService.delete(cart);
	}
}
