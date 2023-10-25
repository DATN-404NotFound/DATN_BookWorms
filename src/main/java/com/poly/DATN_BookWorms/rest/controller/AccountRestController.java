package com.poly.DATN_BookWorms.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.DATN_BookWorms.service.AccountService;


@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class AccountRestController {
	@Autowired
	AccountService accountService;
	

	
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
