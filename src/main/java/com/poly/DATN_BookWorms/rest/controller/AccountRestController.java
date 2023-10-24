package com.poly.DATN_BookWorms.rest.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.dto.AccountDTO;
import com.poly.DATN_BookWorms.dto.LoginDTO;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.response.LoginResponse;
import com.poly.DATN_BookWorms.service.AccountService;


@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class AccountRestController {
//	@Autowired
//	AccountService accountService;
//
//
//
//	@GetMapping("accounts")
//	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
//		if(admin.orElse(false)) {
//			return accountService.getAdministrators();
//		}
//		return accountService.findAll();
//	}
//
//	@PostMapping("accountsManage")
//	public Account create(@RequestBody AccountDTO accountDTO) {
//		Account account  = accountService.create(accountDTO);
//		return account;
//	}
//
//	@PutMapping("accounts/{id}")
//	public Account update(@RequestBody Account account, @PathVariable("id") String username) {
//		return accountService.update(account);
//	}
//
//	@DeleteMapping("accounts/{id}")
//	public void delete(@PathVariable("id") String username) {
//		accountService.delete(username);
//	}
}
