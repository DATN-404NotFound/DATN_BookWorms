package com.poly.DATN_BookWorms.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.DiscountCode;

public interface DiscountcodesRepo extends JpaRepository<DiscountCode, Integer>{

	@Query("Select d from DiscountCode d where d.sale.couoponcode like ?1 and d.account.userid like ?2")
	 DiscountCode findSalesId(String salesid, String userid);
	
	@Query("Select d from DiscountCode d where d.sale.intendfor = 'D' and d.sale.statuses = 'PH'  and d.account.userid like ?1 ")
	 List<DiscountCode> findDisountForSys(String userid);
	
	@Query("Select d from DiscountCode d where d.sale.intendfor like  ?1 and d.sale.statuses = 'PH'  and d.account.userid like ?2 and d.sale.shopid = ?3  ")
	 List<DiscountCode> findDisountOfShopWithUser(String intendFor , String userid, int shopid);
	
	@Query("Select d from DiscountCode d where d.account.userid like ?1")
	 List<DiscountCode> findDisountByUserId(String userid);

	
	@Query("SELECT s.logo FROM DiscountCode d JOIN Account a ON d.userid = a.userid JOIN ShopOnline s ON a.userid = s.userid WHERE d.userid = ?1 OR ?1 IS NULL")
	 DiscountCode findImageShopFromUserId(String id);
}
