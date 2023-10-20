package com.poly.DATN_BookWorms.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Account;

public interface AccountRepo extends JpaRepository<Account, String>{
//	@Query("SELECT o FORM Account o WHERE o.username LIKE ?1")
//	Optional<Account> findByUsername(String username);
//	
//	Account findByEmail(String email);
//
//	Optional<Account> findOneByEmailAndPassword(String email, String password);
//
//	@Query("SELECT o FORM Account o WHERE o.username LIKE ?1 and o.password LIKE ?2")
//	Optional<Account> findOneByUsernameAndPassword(String username, String password);
}
