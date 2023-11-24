package com.poly.DATN_BookWorms.repo;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentShopRepo extends JpaRepository<PaymentShop, Integer> {
    @Query("SELECT o FROM PaymentShop  o WHERE o.shoponlines.shopid = ?1")
    List<PaymentShop> findByShopId(Integer shopid);
}
