package com.poly.DATN_BookWorms.restControllers;

import java.util.List;

import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.poly.DATN_BookWorms.services.ShopOnlineService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/shoponline")
public class ShopOnlineRestController {

	@Autowired
	ShopOnlineService shopOnlineService;
	
	@GetMapping("/{id}")
	public ShopOnline getOne(@PathVariable("id") Integer id) {
		return shopOnlineService.findById(id);
	}
	
	@GetMapping("")
	public List<ShopOnline> getAll() {
		return shopOnlineService.findAll();
	}
}
