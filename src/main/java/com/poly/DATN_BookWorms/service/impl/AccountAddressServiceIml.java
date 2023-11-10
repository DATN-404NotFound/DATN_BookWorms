package com.poly.DATN_BookWorms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.repo.AddressusersRepo;
import com.poly.DATN_BookWorms.service.AccountAddressService;
import com.poly.DATN_BookWorms.service.AccountService;


@Service
public class AccountAddressServiceIml implements AccountAddressService{

	@Autowired
	AddressusersRepo addressusersRepo;
	
	@Override
	public List<Addressusers> getAdressByUser(String userid) {
		// TODO Auto-generated method stub
		return addressusersRepo.findAddressByUser(userid);
	}

}
