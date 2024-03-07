package com.poly.DATN_BookWorms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.PaymentAccount;

public interface PaymentaccountsRepo extends JpaRepository<PaymentAccount, String>{
	@Query("Select p from PaymentAccount p where p.account.userid = ?1")
	public List<PaymentAccount> findWithUser(String userid);
}
