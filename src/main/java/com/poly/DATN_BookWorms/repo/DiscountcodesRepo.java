package com.poly.DATN_BookWorms.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Discountcodes;

public interface DiscountcodesRepo extends JpaRepository<Discountcodes, Integer>{

	@Query("Select d from Discountcodes d where d.sales.couoponcode like ?1 and d.account.userid like ?2")
	public Discountcodes findSalesId(String salesid, String userid);
	
	@Query("Select d from Discountcodes d where d.sales.intendfor = 'D' and d.sales.statuses = 'PH'  and d.account.userid like ?1 ")
	public List<Discountcodes> findDisountForSys(String userid);
	
	@Query("Select d from Discountcodes d where d.sales.intendfor like  ?1 and d.sales.statuses = 'PH'  and d.account.userid like ?2 and d.sales.shopid like ?3  ")
	public List<Discountcodes> findDisountOfShopWithUser(String intendFor ,String userid, int shopid);
	
}
