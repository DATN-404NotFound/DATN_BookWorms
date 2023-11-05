package com.poly.DATN_BookWorms.service.impl;

import com.poly.DATN_BookWorms.entities.Account;
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

    @Override
    public void createShopDefaultWithUser(Account user) {
        Shoponlines shopDefault  = new Shoponlines();

        shopDefault.setAccount(user);
        shopDefault.setShopname("Shop Of "+user.getUsername());
        shopDefault.setDescription("IBook is always a reliable choice for book lovers");
        shopDefault.setIsactive(true);
        shoponlinesRepo.save(shopDefault);
        System.out.println(shopDefault.toString());
    }

    @Override
    public Shoponlines findById(Integer shopId) {
        return shoponlinesRepo.findById(shopId).get();
    }

}
