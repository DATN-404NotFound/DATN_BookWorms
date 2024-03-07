package com.poly.DATN_BookWorms.services.serviceImplements;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Authority;
import com.poly.DATN_BookWorms.repositories.AccountRepo;
import com.poly.DATN_BookWorms.repositories.AuthoritiesRepo;
import com.poly.DATN_BookWorms.services.AuthoritiesService;



@Service
public class IAuthorities implements AuthoritiesService{
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	AuthoritiesRepo authorityRepo;
	
//	@Override
//	public List<Authorities> findAuthoritiesOfAdministrators() {
//		List<Account> accounts = accountRepo.getAdministrators();
//		return authorityRepo.authoritiesOf(accounts);
//	}

	@Override
	public List<Authority> findAll() {
		return authorityRepo.findAll();
	}

	@Override
	public Authority create(Authority authority) {
		 logger.info("create Authorities is Authorities : {}", authority.toString());
		return  authorityRepo.save(authority);
	}

	@Override
	public void delete(String id) {
		logger.info("delete Authorities is id : {}",id);
		try {
			authorityRepo.deleteById(id);
			logger.info("delete Authorities is successfully");
		} catch (Exception e) {
			logger.info("delete Authorities is failed");
			// TODO: handle exception
		}
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> getOneByRole(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Authority authority) {
		logger.info("create Authorities is authorities : {}", authority);
		try {
			authorityRepo.save(authority);
			logger.info("create Authorities is sucessfully ");
		} catch (Exception e) {
			logger.info("create Authorities is failed ");
			// TODO: handle exception
		}
	}

//	@Override
//	public List<Authorities> getOneByRole(String username) {
//		// TODO Auto-generated method stub
//		return authorityRepo.getOneByRole(username);
//	}

}
