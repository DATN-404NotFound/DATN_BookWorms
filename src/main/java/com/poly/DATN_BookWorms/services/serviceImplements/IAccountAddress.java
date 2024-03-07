package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.AddressUser;
import com.poly.DATN_BookWorms.repositories.AddressusersRepo;
import com.poly.DATN_BookWorms.services.AccountAddressService;


@Service
public class IAccountAddress implements AccountAddressService{

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	AddressusersRepo addressusersRepo;
	
	@Override
	public List<AddressUser> getAdressByUser(String userid) {
		// TODO Auto-generated method stub
		logger.info("get list Addressuser with userid : {}", userid);
		return addressusersRepo.findByUserId(userid);
	}

	@Override
	public AddressUser findById(String id){
		return addressusersRepo.findById(id).get();
	}


	@Override
	public AddressUser save(AddressUser id){
		return addressusersRepo.save(id);
	}

}
