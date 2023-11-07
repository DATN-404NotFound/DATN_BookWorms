package com.poly.DATN_BookWorms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Addressusers;

public interface AddressusersRepo extends JpaRepository<Addressusers, String>{

		@Query("Select d from Addressusers d where d.account.userid like ?1")
		public List<Addressusers> findAddressByUser(String userid);
	
}
