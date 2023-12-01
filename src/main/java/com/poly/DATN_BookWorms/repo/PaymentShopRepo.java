package com.poly.DATN_BookWorms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.PaymentShop;
import com.poly.DATN_BookWorms.entities.Shoponlines;

public interface PaymentShopRepo extends JpaRepository<PaymentShop, Integer>{
	@Query("Select DISTINCT ps.shoponlines from PaymentShop ps where ps.shoponlines.shopid = ?1")
	Shoponlines findShopId(int id);
}
