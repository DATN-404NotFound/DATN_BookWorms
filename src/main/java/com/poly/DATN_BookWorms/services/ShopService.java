package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.stereotype.Service;

@Service
public interface ShopService {
    ShopOnline findUserId(String userId);

    void save(ShopOnline shoponline);

    void createShopDefaultWithUser(Account user);

    ShopOnline findById(Integer shopId);

}
