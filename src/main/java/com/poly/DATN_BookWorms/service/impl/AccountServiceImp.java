package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.repo.AccountRepo;
import com.poly.DATN_BookWorms.service.AccountService;

@Service
public class AccountServiceImp  implements AccountService{
	@Autowired
	AccountRepo accountRepo;
//	@Autowired
//	BCryptPasswordEncoder pe;

	@Override
	public Account findById(String username) {
		return accountRepo.findById(username).get();
	}
	
	@Override
	public Account findByEmail(String email) {
		return accountRepo.findByEmail(email);
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
//		return accountRepo.getAdministrators();
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public Account create(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public Account update(Account account) {
		return accountRepo.save(account);
	}
	
	//public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
//		String email = oauth2.getPrincipal().getAttribute("email");
//		String password = Long.toHexString(System.currentTimeMillis());
//		
//		UserDetails user = User.withUsername(email)
//				.password(pe.encode(password)).roles("GUEST").build();
//		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(auth);
	//}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		accountRepo.deleteById(username);
	}
}
