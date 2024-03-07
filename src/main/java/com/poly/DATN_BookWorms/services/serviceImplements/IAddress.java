package com.poly.DATN_BookWorms.services.serviceImplements;

import com.poly.DATN_BookWorms.entities.AddressUser;
import com.poly.DATN_BookWorms.repositories.AddressusersRepo;
import com.poly.DATN_BookWorms.services.AddressService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAddress implements AddressService {

	private static final Logger logger = LogManager.getLogger();
	
    @Autowired
    AddressusersRepo addressusersRepo;
    @Override
    public List<AddressUser> findByAll() {
        return addressusersRepo.findAll();
    }

    @Override
    public List<AddressUser> findByUserId(String userId) {
    	logger.info("list addressUser with userid : {}", userId);
        return addressusersRepo.findByUserId(userId);
    }

	@Override
	public AddressUser create(AddressUser addressusers) {
		return addressusersRepo.save(addressusers);
	}

	@Override
	public AddressUser update(AddressUser addressusers) {
		return addressusersRepo.save(addressusers);
	}

	@Override
	public AddressUser byAddressUserId(String addressusersId) {
		// TODO Auto-generated method stub
		return addressusersRepo.findById(addressusersId).get();
	}

	@Override
	public void delete(String addressusers) {
		addressusersRepo.deleteById(addressusers);
	}
}
