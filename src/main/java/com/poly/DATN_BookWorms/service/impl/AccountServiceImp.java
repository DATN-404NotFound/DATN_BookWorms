package com.poly.DATN_BookWorms.service.impl;


import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.poly.DATN_BookWorms.dto.AccountDTO;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;
import com.poly.DATN_BookWorms.entities.Roles;
import com.poly.DATN_BookWorms.repo.AccountRepo;
import com.poly.DATN_BookWorms.repo.AuthoritiesRepo;
import com.poly.DATN_BookWorms.repo.RoleRepo;

import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.RoleService;
import com.poly.DATN_BookWorms.utils.CRC32Utils;

import java.util.List;


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

	PasswordEncoder passwordEncoder;
	
	@Autowired
	 CRC32_SHA256 crc32Sha256;


	@Override
	public Account findByUsename(String username) {
		return accountRepo.findByUsername(username);
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepo.findByEmail(email);
	}

	@Override
	public List<Account> getAdministrators() {
		return null;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public Account create(Account account) {
		return null;
	}

	@Override
	public Account update(Account account) {
		return null;
	}

	@Override
	public void delete(String username) {

	}

	@Override

	public void save(AccountDTO accountDTO) {
		//Get Role
		Roles role = roleRepo.findById("GUEST").get();

		if (role == null) {
			role = roleRepo.save(new Roles("GUEST","Guest", null));
		}

		//Băm userId and authorrityId
		Long userId = Long.valueOf(crc32Sha256.getCodeCRC32C(accountDTO.getUsername()));
		Long authorityId = Long.valueOf(crc32Sha256.getCodeCRC32C(accountDTO.getUsername()+role.getRoleid()));

		//Set info to account form DTO
		Account account = new Account();
		account.setUserid(userId.toString());
		account.setEmail(accountDTO.getEmail());
		account.setFullname(accountDTO.getFullname());
		account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
		account.setUsername(accountDTO.getUsername());

		//Create account and author for account
		authoritiesRepo.save(new Authorities(authorityId.toString(),account, role));
		accountRepo.save(account);

	}




//	@Override
//	public LoginResponse loginAccount(@RequestBody LoginDTO loginDTO) {

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
//		return new LoginResponse("Login Failed", false);
//	}


//	}


}
