package com.poly.DATN_BookWorms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.DATN_BookWorms.entities.Role;

public interface RoleRepo extends JpaRepository<Role, String> {

	@Query("SELECT o FROM Role o WHERE o.rolename = ?1")
    Role findByName(String string);

	
}
