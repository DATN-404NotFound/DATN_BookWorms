package com.poly.DATN_BookWorms.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Discountcodes;

public interface DiscountcodesRepo extends JpaRepository<Discountcodes, Integer>{

	@Query("Select d from Discountcodes d where d.sales.couoponcode like ?1 and d.account.userid like ?2")
	public Discountcodes findSalesId(String salesid, String userid);
}
