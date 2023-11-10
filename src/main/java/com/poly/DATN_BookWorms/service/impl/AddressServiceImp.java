package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Addressusers;
import com.poly.DATN_BookWorms.repo.AddressusersRepo;
import com.poly.DATN_BookWorms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    AddressusersRepo addressusersRepo;
    @Override
    public List<Addressusers> findByAll() {
        return addressusersRepo.findAll();
    }

    @Override
    public List<Addressusers> findByUserId(String userId) {
        return addressusersRepo.findByUserId(userId);
    }
}
