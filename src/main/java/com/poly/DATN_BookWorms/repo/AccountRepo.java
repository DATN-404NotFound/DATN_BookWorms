package com.poly.DATN_BookWorms.repo;
import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Account;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account, String>{
	
	@Query("SELECT o FROM Account o  WHERE o.username LIKE ?1")
	Account findByUsername(String username);

    Account findByEmail(String email);
}
