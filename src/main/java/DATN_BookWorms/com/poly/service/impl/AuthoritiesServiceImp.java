package com.poly.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entities.Account;
import com.poly.entities.Authorities;
import com.poly.repo.AccountRepo;
import com.poly.repo.AuthoritiesRepo;
import com.poly.service.AuthoritiesService;



@Service
public class AuthoritiesServiceImp implements AuthoritiesService{
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	AuthoritiesRepo authorityRepo;
	
	@Override
	public List<Authorities> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepo.getAdministrators();
		return authorityRepo.authoritiesOf(accounts);
	}

	@Override
	public List<Authorities> findAll() {
		return authorityRepo.findAll();
	}

	@Override
	public Authorities create(Authorities authority) {
		return  authorityRepo.save(authority);
	}

	@Override
	public void delete(String id) {
		authorityRepo.deleteById(id);
	}

	@Override
	public List<Authorities> getOneByRole(String username) {
		// TODO Auto-generated method stub
		return authorityRepo.getOneByRole(username);
	}

}
