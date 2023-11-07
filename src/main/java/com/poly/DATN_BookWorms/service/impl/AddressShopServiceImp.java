package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.AddressShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.AddressShopRepo;
import com.poly.DATN_BookWorms.service.AddressShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressShopServiceImp implements AddressShopService {

    @Autowired
    AddressShopRepo addressShopRepo;

    @Override
    public AddressShop findByShop(Shoponlines shopDetail) {
        return addressShopRepo.findByShop(shopDetail);
    }

    @Override
    public void save(AddressShop addressShop) {
        addressShopRepo.save(addressShop);
    }
}
