package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entities.*;
import com.poly.service.AccountService;
import com.poly.service.AuthoritiesService;
import com.poly.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class AuthorityRestController {
	@Autowired
	AuthoritiesService authorityService;
	
	@GetMapping("authorities")
	public List<Authorities> findAll(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		
		return authorityService.findAll();
	}
	
	@GetMapping("authoritiesOne")
	public List<Authorities> getOneByRole(@RequestParam("username") String username){
		return authorityService.getOneByRole(username);
	}
	
	@PostMapping("authorities")
	public Authorities post(@RequestBody Authorities authority) {
		return authorityService.create(authority);
	}
	
	@DeleteMapping("authorities/{id}")
	public void delete(@PathVariable("id")String id) {
		authorityService.delete(id);	
	}
	
	
}
