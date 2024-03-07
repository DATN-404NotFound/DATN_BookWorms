package com.poly.DATN_BookWorms.repositories;

import com.poly.DATN_BookWorms.entities.AddressShop;
import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressShopRepo extends JpaRepository<AddressShop, Integer> {
    @Query("SELECT o FROM AddressShop o WHERE o.shopOnline = ?1")
    public AddressShop findByShop(ShopOnline shopDetail);
    @Query("SELECT o FROM AddressShop o WHERE o.shopOnline.shopid = ?1")
    List<AddressShop> findByShopId(Integer shopId);
}
