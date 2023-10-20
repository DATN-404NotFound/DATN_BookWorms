package com.poly.DATN_BookWorms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.poly.DATN_BookWorms.dto.AccountDTO;
import com.poly.DATN_BookWorms.dto.LoginDTO;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;
import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.repo.AccountRepo;
import com.poly.DATN_BookWorms.repo.AuthoritiesRepo;
import com.poly.DATN_BookWorms.repo.RoleRepo;
import com.poly.DATN_BookWorms.response.LoginResponse;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.RoleService;

@Service
public class AccountServiceImp implements AccountService {
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	RoleService roleService;
	@Autowired
	AuthoritiesRepo authoritiesRepo;
	@Autowired
	RoleRepo roleRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public Account findById(String username) {
		return accountRepo.findById(username).get();
	}

//	@Override
//	public Account findByEmail(String email) {
//		return accountRepo.findByEmail(email);
//	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public Account create(AccountDTO accountDTO) {
		Account account = new Account(passwordEncoder.encode(accountDTO.getUsername() + accountDTO.getFullname()),
				accountDTO.getUsername(), accountDTO.getFullname(), passwordEncoder.encode(accountDTO.getPassword()),
				null, null, null, null, null, null, null, null, null, null, null);
		Roles roles = roleService.findByName("USER");
		Authorities authorities = new Authorities(null, account, roles);

		authoritiesRepo.save(authorities);
		accountRepo.save(account);
		return account;
	}

	@Override
	public Account update(Account account) {
		return accountRepo.save(account);
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		UserDetails user = User.withUsername(email).password(passwordEncoder.encode(password)).roles("GUEST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		accountRepo.deleteById(username);
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return accountRepo.save(account);
	}

	@Override
	public LoginResponse loginAccount(@RequestBody LoginDTO loginDTO) {
//		// TODO Auto-generated method stub
//		String message = "";
//		Account account1 = accountRepo.findByUsername(loginDTO.getUsername()).get();
//		if (account1!=null) {
//			String password = loginDTO.getPassword();
//			String encodedPassword = account1.getPassword();
//			Boolean isPassRight = passwordEncoder.matches(password, encodedPassword);
//			if (isPassRight) {
//				Optional<Account> account = accountRepo.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
//				if (account.isPresent()) {
//					return new LoginResponse("Login Success",true);
//				}else {
//					return new LoginResponse("Login Failed", false);
//				}
// 			}else {
// 				return new LoginResponse("Password Not Match", false);
// 			}
//		}else {
//			return new LoginResponse("Username not exits", false);
//		}
		return new LoginResponse("Login Failed", false);
	}

	@Override
	public void adRoleToUser(String username, String roleName) {

	}

	@Override
	public Roles saveRole(Roles roles) {
		// TODO Auto-generated method stub
		return null;
	}

}
