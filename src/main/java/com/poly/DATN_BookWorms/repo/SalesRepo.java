package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Sales;

public interface SalesRepo extends JpaRepository<Sales, String>{

        @Query("Select m from Sales m where m.shopid is not null and m.intendfor like ?1")
        List<Sales> sales_of_shop_for_intendfor(String intendFor) ;
}
