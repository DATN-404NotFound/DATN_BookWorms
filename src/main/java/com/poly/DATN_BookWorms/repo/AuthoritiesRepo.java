package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Authorities;

public interface AuthoritiesRepo extends JpaRepository<Authorities, String>{

	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authorities> authoritiesOf(List<Account> accounts);

	@Query("SELECT a FROM Authority a WHERE a.account.username LIKE ?1")
	List<Authorities> getOneByRole(String username);
}
