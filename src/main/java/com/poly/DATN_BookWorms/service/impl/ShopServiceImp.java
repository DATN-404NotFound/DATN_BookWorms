package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Shoponlines;
import com.poly.DATN_BookWorms.repo.ShoponlinesRepo;
import com.poly.DATN_BookWorms.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImp  implements ShopService {

    @Autowired
    ShoponlinesRepo shoponlinesRepo;
    @Override
    public Shoponlines findUserId(String userId) {
        return shoponlinesRepo.findByUserId(userId);
    }

    @Override
    public void save(Shoponlines shoponlines) {
        shoponlinesRepo.save(shoponlines);
    }
}
