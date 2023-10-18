package com.poly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entities.Roles;

public interface RoleRepo extends JpaRepository<Roles, String>{

}
