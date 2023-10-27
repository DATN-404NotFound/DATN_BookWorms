package com.poly.DATN_BookWorms.repo;
import org.springframework.data.jpa.repository.JpaRepository;


import com.poly.DATN_BookWorms.entities.Account;


public interface AccountRepo extends JpaRepository<Account, String>{

//	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
//	List<Account> getAdministrators();

	public Account findByEmail(String email);
}
