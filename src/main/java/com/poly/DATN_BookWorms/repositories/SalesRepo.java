package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SalesRepo extends JpaRepository<Sale, String>{

        @Query("Select m from Sale m where m.shopid is not null and m.intendfor like ?1")
        List<Sale> sales_of_shop_for_intendfor(String intendFor) ;
        
        @Query("select m from Sale m where  m.statuses = 'PH' and m.shopid = ?1 and m.intendfor like ?2")
        List<Sale> getSaleByShopAndByIntendfor(int shopId , String intendFor);

        List<Sale> findAllByshopid(Integer shopid);
}
