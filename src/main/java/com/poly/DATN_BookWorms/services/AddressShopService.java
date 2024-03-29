package com.poly.DATN_BookWorms.services;

import com.poly.DATN_BookWorms.entities.AddressShop;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressShopService {
    public AddressShop findByShop(ShopOnline shopDetail) ;

    void save(AddressShop addressShop);

    List<AddressShop> findByShopid(Integer shopid);

    AddressShop findById(Integer addressShopId);
}
