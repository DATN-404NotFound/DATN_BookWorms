package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.repo.AddressusersRepo;
import com.poly.DATN_BookWorms.service.AddressService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    AddressusersRepo addressusersRepo;
    @Override
    public List<Addressusers> findByAll() {
        return addressusersRepo.findAll();
    }

    @Override
    public List<Addressusers> findByUserId(String userId) {
    	logger.info("list addressUser with userid : {}", userId);
        return addressusersRepo.findByUserId(userId);
    }
}
