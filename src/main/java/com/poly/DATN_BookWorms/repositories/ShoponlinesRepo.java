package com.poly.DATN_BookWorms.repositories;

import com.poly.DATN_BookWorms.entities.ShopOnline;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface ShoponlinesRepo extends JpaRepository<ShopOnline, Integer> {

    @Query(value = "SELECT o FROM ShopOnline o where o.account.userid = ?1")
    ShopOnline findByUserId(String userId);
}
