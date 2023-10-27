package com.poly.DATN_BookWorms.service;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.dto.AccountDTO;
import com.poly.DATN_BookWorms.entities.Account;


@Service
public interface AccountService {
	
	public Account findByUsename(String username);
	
//	public Account findByEmail(String email);
//
//	public List<Account> getAdministrators();
//
//	public List<Account> findAll();
//
//	public Account create(Account account);
//
//	public Account update(Account account);
//	
//	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2);
//	
//	void delete(String username);

	void save(AccountDTO accountDTO);
	

	
	
}
