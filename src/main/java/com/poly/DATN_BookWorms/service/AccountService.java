package com.poly.DATN_BookWorms.service;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.dto.AccountDTO;
import com.poly.DATN_BookWorms.dto.LoginDTO;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;
import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.response.LoginResponse;



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
