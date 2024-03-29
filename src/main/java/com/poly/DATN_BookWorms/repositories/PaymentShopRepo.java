package com.poly.DATN_BookWorms.repositories;


import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.ShopOnline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentShopRepo extends JpaRepository<PaymentShop, Integer> {
    @Query("SELECT o FROM PaymentShop  o WHERE o.shopOnline.shopid = ?1")
    List<PaymentShop> findByShopId(Integer shopId);
    @Query("SELECT COALESCE(SUM(o.valuepayment), 0.0) FROM PaymentShop o WHERE o.shopOnline.shopid = :shopId and o.createat >= :startDate and o.createat <= :endDate and o.status = :status")
    Float getMonthPaid(@Param("shopId") Integer shopId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") boolean status);
    @Query("SELECT  SUM(o.valuepayment) FROM PaymentShop  o WHERE o.shopOnline.shopid = ?1 and o.status =?2")
    Float getTotal(Integer shopId, boolean statusPayment);
    @Query("Select DISTINCT ps.shopOnline from PaymentShop ps where ps.shopOnline.shopid = ?1")
	ShopOnline findShopId(int id);
}
